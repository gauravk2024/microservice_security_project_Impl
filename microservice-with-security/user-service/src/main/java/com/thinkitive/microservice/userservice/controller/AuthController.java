package com.thinkitive.microservice.userservice.controller;

import com.thinkitive.microservice.userservice.dto.JwtRequest;
import com.thinkitive.microservice.userservice.dto.JwtResponse;
import com.thinkitive.microservice.userservice.entity.Patient;
import com.thinkitive.microservice.userservice.entity.User;
import com.thinkitive.microservice.userservice.service.AuthenticationService;
import com.thinkitive.microservice.userservice.service.PatientService;
import com.thinkitive.microservice.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UserService userService;

    @Autowired(required = false)
    private PatientService patientService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        UserDetails userDetails = authenticationService.authenticate(request.getEmail(), request.getPassword());
        String token = authenticationService.generateJwtToken(userDetails);
        JwtResponse response = new JwtResponse(token, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/create")
    public User createUserMethod(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/patient/create")
    public ResponseEntity<Patient> createPatientData(@RequestBody Patient patient){
        if (patientService != null) {
            Patient createdPatient = patientService.createPatient(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}