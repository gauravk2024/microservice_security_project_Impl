package com.thinkitive.microservice.userservice.service;

import com.thinkitive.microservice.userservice.entity.Patient;
import com.thinkitive.microservice.userservice.entity.User;
import com.thinkitive.microservice.userservice.exception.UserNotFoundException;
import com.thinkitive.microservice.userservice.exception.UserServiceException;
import com.thinkitive.microservice.userservice.feignservice.PatientFeignService;
import com.thinkitive.microservice.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PatientFeignService patientFeignService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(int id) {
        User getUser = userRepository.findById(id).orElseThrow(() -> new UserServiceException("User not found with id: " + id));
//        ArrayList<Patient> patient = restTemplate.getForObject("http://PATIENT-SERVICE/patient/get/"+getUser.getId(), ArrayList.class);
        Patient patients = patientFeignService.getPatient(getUser.getId());
        getUser.setPatient(patients);
        return getUser;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User createUser = userRepository.save(user);
        return createUser;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(int id, User user) {
        User updateInfo = userRepository.findById(id).orElse(null);
        if (updateInfo != null) {
            updateInfo.setUsername(user.getUsername());
            updateInfo.setEmail(user.getEmail());
            updateInfo.setPassword(user.getPassword());
            User updatedStatus = userRepository.save(updateInfo);
            return updatedStatus;
        } else {
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> getUserAll = userRepository.findAll();
        return getUserAll;
    }
}