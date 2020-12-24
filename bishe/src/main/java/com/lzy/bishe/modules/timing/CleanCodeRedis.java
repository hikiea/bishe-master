package com.lzy.bishe.modules.timing;

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

    @Scheduled(fixedDelay = 60000)
    public void fixedDelayJob() throws InterruptedException {
        redisCodeUtil.cleanRedis();
        System.out.println("清理验证码:" + new Date());
    }


}
