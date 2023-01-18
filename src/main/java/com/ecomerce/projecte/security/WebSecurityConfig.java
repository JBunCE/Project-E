package com.ecomerce.projecte.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5173","http://localhost:5173"
                        )
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD")
                        .allowedHeaders("*")
                        .exposedHeaders("*");
                }	
        };
    
}
}
