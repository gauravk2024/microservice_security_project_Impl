package com.thinkitive.microserive.patientservice.controller;

import com.thinkitive.microserive.patientservice.entity.Patient;
import com.thinkitive.microserive.patientservice.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientServiceImpl service;

    @GetMapping("/get/{id}")
    public ResponseEntity<Patient> getOnePatient(@PathVariable int id){
        Patient getPatient = service.getOnePatient(id);
        return ResponseEntity.ok().body(getPatient);
    }

    @PostMapping("/create")
    public ResponseEntity<Patient> createOnePatient(@RequestBody Patient patient){
        Patient createPatient = service.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPatient);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updateOnePatient(@PathVariable int id, @RequestBody Patient patient){
        Patient updatePatient = service.updatePatient(id,patient);
        return ResponseEntity.ok(updatePatient);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> getAllPatient = service.getAllList();
        return ResponseEntity.status(HttpStatus.FOUND).body(getAllPatient);
    }

    @DeleteMapping("delete")
    public void deleteByOne(@PathVariable int id){
        service.deletePatient(id);
    }
}