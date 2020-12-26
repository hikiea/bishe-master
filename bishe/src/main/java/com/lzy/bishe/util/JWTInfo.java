package com.lzy.bishe.util;

import com.auth0.jwt.JWT;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lzy
 */
@Data
public class JWTInfo<T> {

    public static String getUserId(HttpServletRequest httpServletRequest){
        String id = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        return id;
    }

    public static String getPower(HttpServletRequest httpServletRequest){
        String power = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(2);
        return power;
    }

    public static String getUserName(HttpServletRequest httpServletRequest){
        String username = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(3);
        return username;
    }

}
