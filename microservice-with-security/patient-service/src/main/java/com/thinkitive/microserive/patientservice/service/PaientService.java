package com.thinkitive.microserive.patientservice.service;

import com.thinkitive.microserive.patientservice.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PaientService {
    Patient getOnePatient(int id);
    Patient createPatient(Patient patient);
    Patient updatePatient(int id, Patient patient);
    void deletePatient(int id);
    List<Patient> getAllList();
}
