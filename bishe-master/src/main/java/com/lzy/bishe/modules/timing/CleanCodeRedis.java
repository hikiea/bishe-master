package com.lzy.bishe.modules.timing;

import com.lzy.bishe.modules.user.mapper.UserMapper;
import com.lzy.bishe.redis.RedisCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lzy
 */
@Component
public class CleanCodeRedis {

    @Autowired
    private RedisCodeUtil redisCodeUtil;

    @Autowired
    private UserMapper userMapper;

    @Scheduled(fixedDelay = 60000)
    public void fixedDelayJob() throws InterruptedException {
        redisCodeUtil.cleanRedis();
        userMapper.findUserById(1);
        System.out.println("清理验证码:" + new Date());
    }


}
