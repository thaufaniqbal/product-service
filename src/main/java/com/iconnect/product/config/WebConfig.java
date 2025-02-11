package com.iconnect.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://banyu-ijo-4l8e.vercel.app", "http://192.168.100.51:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization")
                .exposedHeaders("Authorization", "Content-Type", "X-Requested-With", "Accept",
                        "Origin", "Cache-Control", "Content-Disposition")
                .allowCredentials(true)
                .maxAge(3600);
    }
}