package com.lzy.bishe.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lzy.bishe.annotation.PassToken;
import com.lzy.bishe.annotation.UserLoginToken;
import com.lzy.bishe.modules.user.model.dto.order.RedisBlackToken;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.modules.user.service.UserService;
import com.lzy.bishe.redis.RedisTokenUtil;
import com.lzy.bishe.util.DateUtil;
import com.lzy.bishe.util.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author lizhongyi
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTokenUtil redisTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);

            if (userLoginToken.required()) {
                // 执行认证
                if (token.equals("")) {
                    throw new RuntimeException("无token，请重新登录");
                }
                if (redisTokenUtil.get(token) != null){
                    throw new RuntimeException("token失效，请重新登录");
                }
                // 验证 token 是否已过期
                try{
                    Date expiresAt = JWT.decode(token).getExpiresAt();
                    if (expiresAt.getTime() < System.currentTimeMillis()){
                        RedisBlackToken blackToken = new RedisBlackToken();
                        blackToken.setId(JWT.decode(token).getAudience().get(0));
                        blackToken.setUsername(JWT.decode(token).getAudience().get(1));
                        blackToken.setPower(JWT.decode(token).getAudience().get(2));
                        blackToken.setTime(DateUtil.getNowDate());
                        redisTokenUtil.set(token,blackToken);
                        throw new RuntimeException("token超过存活时间，已失效，请重新登录");
                    }
                }catch (JWTDecodeException j){
                    throw new RuntimeException("请重新登录");
                }
                // 获取 token 中的 user id
                Integer userId;
                try {
                    userId = JWTInfo.getUserId_int(httpServletRequest);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                User user = userService.findUserById(userId);
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
