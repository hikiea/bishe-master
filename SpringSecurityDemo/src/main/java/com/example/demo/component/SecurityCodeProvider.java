package com.example.demo.component;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
public class SecurityCodeProvider implements AuthenticationProvider {

    protected Log log = LogFactory.getLog(this.getClass());

    private static UserDao userDao;

    @Autowired
    private void setUserDao(UserDao userDao) {
        SecurityCodeProvider.userDao = userDao;
    }

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("now start custom authenticate process!");
        MyWebAuthenticationDetails details = (MyWebAuthenticationDetails) authentication.getDetails();

        //校验码判断
//        if (!details.getValidCode().equals(details.getSessionCodeValue())) {
//            log.info("验证码错误");
//            throw new BadCredentialsException("验证码错误");
//        }

        //测试需要，临时关闭
        //校验码有效期
        if((new Date()).getTime() - details.getSessionCodeTime() > 60000) {
            log.info("验证码超时");
            throw new BadCredentialsException("验证码超时");
        }

        //验密
        boolean matches = false;
        User user = null;
        try {
            user = userDao.findUserByUserName(details.getUsername());
            matches = bCryptPasswordEncoder.matches(details.getPassword(),user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //异常抛出
        if (!matches) {
            log.info("密码错误");
            throw new BadCredentialsException("密码错误");
        }

        Collection<GrantedAuthority> auths = new ArrayList<>();
        SimpleGrantedAuthority user_role = new SimpleGrantedAuthority(user.getRoles());
        auths.add(user_role);
        return new UsernamePasswordAuthenticationToken
                (details.getUsername(), details.getPassword(), auths);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
