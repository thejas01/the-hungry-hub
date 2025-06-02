package com.thejas.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        
        // Allow specific origin
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        
        // Allow all HTTP methods including OPTIONS for preflight
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"));
        
        // Allow all headers
        corsConfig.setAllowedHeaders(Arrays.asList("*"));
        
        // Allow credentials (cookies, authorization headers)
        corsConfig.setAllowCredentials(true);
        
        // Set max age for preflight requests cache (in seconds)
        corsConfig.setMaxAge(3600L);
        
        // Expose headers that the client can access
        corsConfig.setExposedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}