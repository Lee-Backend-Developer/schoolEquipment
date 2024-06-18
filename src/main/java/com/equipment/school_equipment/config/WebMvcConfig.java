package com.equipment.school_equipment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("file:///Users/leemac/IdeaProjects/img/")
                .addResourceLocations("file:///home/ec2-user/img/")
                .addResourceLocations("classpath:/static/");
    }
}
