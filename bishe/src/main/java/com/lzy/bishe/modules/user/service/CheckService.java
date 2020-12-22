package com.lzy.bishe.modules.user.service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.lzy.bishe.modules.base.model.entity.ResultDTO;
import com.lzy.bishe.modules.jwt.service.TokenService;
import com.lzy.bishe.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.redis.RedisCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhongyi
 */
@Slf4j
@Service
public class CheckService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCodeUtil redisCodeUtil;

    public ResultDTO checkLogin(UserLoginDTO userLoginInfo) {
        Object code2 = redisCodeUtil.get(userLoginInfo.getCode());
        String code = userLoginInfo.getCode();
        JSONObject jsonObject=new JSONObject();
        if (!code.equals(code2)){
            redisCodeUtil.del(code);
            return ResultDTO.errorOf(500,"验证码错误");
        }
        User userForBase=userService.findByUsername(userLoginInfo.getUsername());
        if(userForBase==null){
            redisCodeUtil.del(code);
            return ResultDTO.errorOf(500,"登录失败，用户不存在");
        }else if(!userForBase.getPassword().equals(userLoginInfo.getPassword())){
            redisCodeUtil.del(code);
            return ResultDTO.errorOf(500,"登录失败，密码错误");
        }else {
            redisCodeUtil.del(code);
            String token = tokenService.getToken(userForBase);
            jsonObject.put("token", token);
            User u = userService.findUserById(JWT.decode(token).getAudience().get(0));
            u.setPassword("****************");
            jsonObject.put("user", u);
            log.info("用户：" + userLoginInfo.getUsername() + "登录");
            return ResultDTO.successOf("登录成功",jsonObject);
        }
    }

    public boolean checkPowerByAdmin(HttpServletRequest request){
        String token = request.getHeader("token");
        String power = JWT.decode(token).getAudience().get(2);
        if ("admin".equals(power)){
            return true;
        } else {
            return false;
        }
    }
}
