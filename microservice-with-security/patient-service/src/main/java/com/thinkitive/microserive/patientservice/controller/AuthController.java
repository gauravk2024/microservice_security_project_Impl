package com.thinkitive.microserive.patientservice.controller;

import com.thinkitive.microserive.patientservice.dto.JwtRequest;
import com.thinkitive.microserive.patientservice.dto.JwtResponse;
import com.thinkitive.microserive.patientservice.entity.Patient;
import com.thinkitive.microserive.patientservice.service.AuthenticationService;
import com.thinkitive.microserive.patientservice.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PatientServiceImpl patientService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        UserDetails userDetails = authenticationService.authenticate(request.getEmail(), request.getPassword());
        String token = authenticationService.generateJwtToken(userDetails);
        JwtResponse response = new JwtResponse(token, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/patient/create")
    public Patient createUserMethod(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }
}