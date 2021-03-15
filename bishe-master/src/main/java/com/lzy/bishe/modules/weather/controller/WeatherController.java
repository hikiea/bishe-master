package com.lzy.bishe.modules.weather.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lzy
 */
@RestController
@RequestMapping("/api/weather")
@Api(tags = {"WeatherController"}, description = "天气相关接口")
public class WeatherController {

    @CrossOrigin
    @GetMapping("/getWeather/{city}")
    public Object test(@PathVariable(name = "city") String city){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.seniverse.com/v3/weather/now.json?key=SiuwJAueYhN7oaicw&location=" + city +"&language=zh-Hans&unit=c";
        String trans = restTemplate.getForObject(url,String.class);
        return trans;
    }

}
