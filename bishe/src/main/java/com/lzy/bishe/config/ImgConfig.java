package com.lzy.bishe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImgConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/static/picture/**")
//                 .addResourceLocations("file:" + "C:\\Users\\Administrator\\Desktop\\img\\");
                 .addResourceLocations("file:" + "C:\\Users\\Lzy\\Desktop\\img\\");
    }

}