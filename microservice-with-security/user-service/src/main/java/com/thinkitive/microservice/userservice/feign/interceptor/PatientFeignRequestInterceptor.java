package com.thinkitive.microservice.userservice.feign.interceptor;

import com.thinkitive.microservice.userservice.security.JwtHelper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class PatientFeignRequestInterceptor implements RequestInterceptor {

    private final JwtHelper jwtHelper;

    public PatientFeignRequestInterceptor(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public void apply(RequestTemplate template) {
        String token = getToken();
        if (token != null) {
            System.out.println("Generated Token: " + token);
            template.header("Authorization", "Bearer " + token);
        } else {
            throw new IllegalStateException("No authentication token found");
        }
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtHelper.generateToken(userDetails);
        }
        return null;
    }
}