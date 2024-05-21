package com.thinkitive.microservice.userservice.controller;

import com.thinkitive.microservice.userservice.entity.Patient;
import com.thinkitive.microservice.userservice.feignservice.PatientFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class PatientController {
    @Autowired
    private PatientFeignService patientService;

  @GetMapping("/patient/get/{id}")
    public Patient getPatient(@PathVariable int id) {
        return patientService.getPatient(id);
    }

    @PostMapping("/patient/create")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @GetMapping("/patient/getall")
    public List<Patient> getAllPatient(){
        return patientService.getAllPatientData();
    }
}