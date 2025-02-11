package com.iconnect.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("https://banyu-ijo-4l8e.vercel.app", "https://another-allowed-origin.com") // Tambahkan origin yang diperbolehkan
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Cache-Control")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
