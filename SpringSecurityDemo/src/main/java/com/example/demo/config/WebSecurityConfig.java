package com.example.demo.config;

import com.alibaba.fastjson.JSON;


import com.example.demo.component.SecurityCodeProvider;
import com.example.demo.dto.ResultDTO;
//import com.example.demo.service.MyUserDetailsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    代码大幅参考自 https://www.jianshu.com/p/62a0a9a78530
//    https://zhuanlan.zhihu.com/p/47584036


    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //添加带验证码校验的密码验证类
        auth.authenticationProvider(new SecurityCodeProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().permitAll().and().logout().permitAll();
        //想关掉springSecurity就用这个
        http
                .exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and()
                .csrf().disable()//关闭对跨域请求的限制
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN","ROOT")
                .antMatchers("/admin/**").hasAnyRole("ADMIN","ROOT")
                .antMatchers("/root/**").hasAnyRole("ROOT")
                .anyRequest().authenticated()//每个请求都必须被认证
                .and()
                    .formLogin()
                    .successHandler(new ApiLoginSuccessHandler())
                    .failureHandler(new ApiLoginFailureHandler())
                .loginProcessingUrl("/login")//接口地址
                .authenticationDetailsSource(authenticationDetailsSource)//认证类扩展，增添验证码功能
                .and()
                    .logout().logoutSuccessHandler(new ApiLogoutSuccessHandler())//登出监听器
                    .logoutUrl("/logout");//登出接口
    }

    //定义异常返回信息
    public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.sendError(HttpStatus.UNAUTHORIZED.value(),authException.getMessage());
        }

    }

    //登录成功监听器
    private class ApiLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

            //登录成功后移除session中验证码信息
            request.getSession().removeAttribute("codeValue");
            request.getSession().removeAttribute("codeTime");

            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            String s = JSON.toJSONString(ResultDTO.okOf("登录成功"));
            out.write(s);
            out.flush();
            out.close();
        }
    }

    //登录失败监听器
    private class ApiLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

            //登录失败后移除session中验证码信息
            request.getSession().removeAttribute("codeValue");
            request.getSession().removeAttribute("codeTime");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            String message = exception.getMessage();
            ResultDTO resultDTO = ResultDTO.errorOf(2001,message);
            String s = JSON.toJSONString(resultDTO);
            out.write(s);
            out.flush();
            out.close();
        }
    }

    //登出成功监听器
    private class ApiLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {

            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            ResultDTO resultDTO = ResultDTO.okOf("登出成功");
            out.write(resultDTO.getMessage());
            out.flush();
            out.close();
        }
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(Duration.ofHours(1));
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
