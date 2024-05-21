//package com.thinkitive.microserive.patientservice.config;
//
//import com.thinkitive.microserive.patientservice.dto.JwtRequest;
//import com.thinkitive.microserive.patientservice.dto.JwtResponse;
//import com.thinkitive.microserive.patientservice.entity.Patient;
//import com.thinkitive.microserive.patientservice.security.JwtHelper;
//import com.thinkitive.microserive.patientservice.service.PatientServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private AuthenticationManager manager;
//
//   @Autowired
//   private PatientServiceImpl patientService;
//
//    @Autowired
//    private JwtHelper helper;
//
//    private Logger logger = LoggerFactory.getLogger(AuthController.class);
//
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//
//        doAuthenticate(request.getEmail(), request.getPassword());
//
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        String token = this.helper.generateToken(userDetails);
//
//        JwtResponse response = new JwtResponse(token, userDetails.getUsername());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    private void doAuthenticate(String email, String password) {
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        try {
//            manager.authenticate(authentication);
//
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password  !!");
//        }
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public String exceptionHandler() {
//        return "Credentials Invalid !!";
//    }
//
//    @PostMapping("/patient/create")
//    public Patient createUserMethod(@RequestBody Patient patient){
//        return patientService.createPatient(patient);
//    }
//}