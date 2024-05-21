package com.thinkitive.microserive.patientservice.service;

import com.thinkitive.microserive.patientservice.entity.Patient;
import com.thinkitive.microserive.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class PatientServiceImpl implements PaientService{

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Patient getOnePatient(int id) {
        Patient getOne = patientRepository.findById(id).get();
        return getOne;
    }

    @Override
    public Patient createPatient(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        Patient createOne = patientRepository.save(patient);
        return createOne;
    }

    @Override
    public Patient updatePatient(int id, Patient patient) {
        Patient updateOne = patientRepository.findById(id).get();
        updateOne.setFirstName(patient.getFirstName());
        updateOne.setLastName(patient.getLastName());
        updateOne.setDateOfBirth(patient.getDateOfBirth());
        updateOne.setPhoneNumber(patient.getPhoneNumber());
        return patientRepository.save(updateOne);
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getAllList() {
        List<Patient> listAllPatient = patientRepository.findAll();
        return listAllPatient;
    }
}