package com.thinkitive.microservice.userservice.service;


import com.thinkitive.microservice.userservice.entity.Patient;
import com.thinkitive.microservice.userservice.feignservice.PatientFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientFeignService patientFeignService;

    public Patient createPatient(Patient patient) {
        return patientFeignService.createPatient(patient);
    }
}