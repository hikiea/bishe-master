package com.lzy.bishe.modules.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.util.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


/**
 * @author lizhongyi
 */
@Service("TokenService")
public class TokenService {

    public String getToken(User user) {
        String uuid = UUID.randomUUID().toString();
        String token="";
        String userId = user.getId().toString();
        try{
            // 将 需要的信息 保存到 token 里面
            token= JWT.create()
                    // 承载用户信息
                    .withAudience(userId,user.getUsername(),user.getPower(),uuid)
                    // 生成 签名的时间
                    .withIssuedAt(new Date())
                    // 生成 签名的有效期,分钟
                    .withExpiresAt(DateUtil.offset(new Date(),10080, Calendar.MINUTE))
                    // 以 password 作为 token 的密钥
                    .sign(Algorithm.HMAC256(user.getPassword()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
}
