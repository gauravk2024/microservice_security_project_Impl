package com.thinkitive.microservice.userservice.service;

import com.thinkitive.microservice.userservice.config.CustomUser;
import com.thinkitive.microservice.userservice.entity.User;
import com.thinkitive.microservice.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(CustomUser::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exist"));
    }
}