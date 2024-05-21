package com.thinkitive.microservice.userservice.feignservice;

import com.thinkitive.microservice.userservice.feign.config.PatientFeignClientConfiguration;
import com.thinkitive.microservice.userservice.entity.Patient;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "PATIENT-SERVICE", configuration = PatientFeignClientConfiguration.class)
public interface PatientFeignService {

    @GetMapping("/patient/get/{id}")
    @Headers("Content-Type: application/json")
    Patient getPatient(@PathVariable int id);

    @PostMapping("/patient/create")
    @Headers("Content-Type: application/json")
    Patient createPatient(@RequestBody Patient patient);

    @GetMapping("/patient/getall")
    @Headers("Content-Type: application/json")
    List<Patient> getAllPatientData();
}