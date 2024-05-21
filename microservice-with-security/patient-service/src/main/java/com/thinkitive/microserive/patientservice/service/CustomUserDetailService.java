package com.thinkitive.microserive.patientservice.service;

import com.thinkitive.microserive.patientservice.entity.Patient;
import com.thinkitive.microserive.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient user = patientRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));
        return user;
    }
}