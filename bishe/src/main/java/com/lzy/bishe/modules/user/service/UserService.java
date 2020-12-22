package com.lzy.bishe.modules.user.service;

import com.auth0.jwt.JWT;
import com.lzy.bishe.modules.base.model.entity.ResultDTO;
import com.lzy.bishe.modules.user.mapper.UserMapper;
import com.lzy.bishe.modules.user.model.dto.responseDTO.UpdateUserInfoDTO;
import com.lzy.bishe.modules.user.model.dto.order.RedisBlackToken;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.redis.RedisTokenUtil;
import com.lzy.bishe.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * @author lizhongyi
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CheckService checkService;

    @Autowired
    private RedisTokenUtil redisTokenUtil;

    public User findByUsername(String username){
        return userMapper.findByUsernameToToken(username);
    }

    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    public ResultDTO getUserMessage(HttpServletRequest httpServletRequest) {
        String id = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        User userMessage = userMapper.getUserMessage(id);
        userMessage.setPassword(null);
        return ResultDTO.successOf("用户获取成功！",userMessage);
    }


    public ResultDTO logout(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        RedisBlackToken blackToken = new RedisBlackToken();
        blackToken.setId(JWT.decode(token).getAudience().get(0));
        blackToken.setUsername(JWT.decode(token).getAudience().get(1));
        blackToken.setPower(JWT.decode(token).getAudience().get(2));
        blackToken.setTime(DateUtil.getNowDate());
        redisTokenUtil.set(token,blackToken);
        log.info("用户：" + JWT.decode(token).getAudience().get(1) + "已登出");
        return ResultDTO.successOf("登出成功");
    }

    public ResultDTO registerUser(User user) {
        if (checkEqualUsername(user.getUsername()) != null){
            return ResultDTO.errorOf(500,"用户名不可重复");
        }else{
            user.setCreateTime(LocalDateTime.now());
            user.setNickname(UUID.randomUUID().toString());
            userMapper.registerUser(user);
            return ResultDTO.successOf("用户注册成功","请及时更改昵称");
        }
    }

    public ResultDTO updateUserMessage(UpdateUserInfoDTO userInfo) {
        userMapper.updateUserMessage(userInfo);
        return ResultDTO.successOf("修改成功");
    }

    public User checkEqualUsername(String username){
        return userMapper.checkEqualUsername(username);
    }

}
