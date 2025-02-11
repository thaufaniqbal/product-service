package com.iconnect.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("https://banyu-ijo-4l8e.vercel.app", "http://192.168.100.51:5173/") // Tambahkan origin yang diperbolehkan
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("'Access-Control-Allow-Origin'")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
