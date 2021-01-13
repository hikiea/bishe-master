package com.lzy.bishe.modules.user.service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.lzy.bishe.modules.email.service.EmailService;
import com.lzy.bishe.util.JWTInfo;
import com.lzy.bishe.util.ResultDTO;
import com.lzy.bishe.modules.jwt.service.TokenService;
import com.lzy.bishe.modules.user.mapper.UserMapper;
import com.lzy.bishe.modules.user.model.dto.requestDTO.UserLoginDTO;
import com.lzy.bishe.modules.user.model.dto.responseDTO.UpdateUserInfoDTO;
import com.lzy.bishe.modules.user.model.dto.order.RedisBlackToken;
import com.lzy.bishe.modules.user.model.entity.User;
import com.lzy.bishe.redis.RedisCodeUtil;
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
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCodeUtil redisCodeUtil;

    @Autowired
    private RedisTokenUtil redisTokenUtil;

    @Autowired
    private EmailService emailService;

    public User findByUsername(String username){
        return userMapper.findByUsernameToToken(username);
    }

    public User findUserById(Integer userId) {
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
        blackToken.setTime(DateUtil.getNowDate());
        redisTokenUtil.set(token,blackToken);
        log.info("用户：" + JWT.decode(token).getAudience().get(3) + "已登出");
        return ResultDTO.successOf("登出成功");
    }

    public ResultDTO registerUser(User user) {
        if (checkEqualUsername(user.getUsername()) != null){
            return ResultDTO.errorOf(500,"用户名不可重复");
        }else{
            user.setCreateTime(LocalDateTime.now());
            user.setPublishStatus(0);
            userMapper.registerUser(user);
            try{
//                emailService.sendEmail(user.getEmail(),"大小一家欢迎您","大小一家欢迎您的加入，请体验我们的服务");
            }catch (Exception e){
                return ResultDTO.errorOf(500,"邮件服务调用失败");
            }
            return ResultDTO.successOf("用户注册成功");
        }
    }

    public ResultDTO updateUserMessage(UpdateUserInfoDTO userInfo) {
        userMapper.updateUserMessage(userInfo);
        return ResultDTO.successOf("修改成功");
    }

    public User checkEqualUsername(String username){
        return userMapper.checkEqualUsername(username);
    }

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
            User u = userService.findUserById(Integer.parseInt(JWT.decode(token).getAudience().get(0)));
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

    public ResultDTO getOneUser(String id) {
        User userMessage = userMapper.getUserMessage(id);
        return ResultDTO.successOf("获取成功",userMessage);
    }

    public ResultDTO changeHead(HttpServletRequest httpServletRequest, String headUrl) {
        Integer id = JWTInfo.getUserId_int(httpServletRequest);
        userMapper.changeHead(id,headUrl);
        return ResultDTO.successOf("头像更改成功");
    }
}
