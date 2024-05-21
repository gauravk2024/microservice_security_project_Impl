package com.thinkitive.microservice.userservice.feign.config;

import com.thinkitive.microservice.userservice.feign.interceptor.PatientFeignRequestInterceptor;
import com.thinkitive.microservice.userservice.security.JwtHelper;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PatientFeignClientConfiguration {

    private final JwtHelper jwtHelper;

    public PatientFeignClientConfiguration(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new PatientFeignRequestInterceptor(jwtHelper);
    }
}