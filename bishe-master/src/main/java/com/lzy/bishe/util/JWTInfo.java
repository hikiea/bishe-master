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
        return JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
    }

    public static Integer getUserId_int(HttpServletRequest httpServletRequest){
        return  Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0));
    }

    public static String getAddress(HttpServletRequest httpServletRequest){
        return JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(1);
    }

    public static String getPower(HttpServletRequest httpServletRequest){
        return JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(2);
    }

    public static String getUserName(HttpServletRequest httpServletRequest){
        return JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(3);
    }

    public static String getUserNickName(HttpServletRequest httpServletRequest){
        return JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(4);
    }

    public static String getUserCommunityId(HttpServletRequest httpServletRequest){
        return JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(5);
    }

    public static String getPublishStatus(HttpServletRequest httpServletRequest){
        return JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(6);
    }

}
