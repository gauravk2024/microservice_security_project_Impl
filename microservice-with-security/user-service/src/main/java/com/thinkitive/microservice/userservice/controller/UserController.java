package com.thinkitive.microservice.userservice.controller;

import com.thinkitive.microservice.userservice.entity.User;
import com.thinkitive.microservice.userservice.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "UserController REST Operation")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User getUser = userService.getUser(id);
        return ResponseEntity.ok().body(getUser);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a USer", description = "UserCreation Post Method!!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Success | Ok"),
            @ApiResponse(responseCode = "401", description = "Not authorized !!"),
            @ApiResponse(responseCode = "201", description = "new user created !!")
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<User> updateUserInfo(@PathVariable int id, @RequestBody User user){
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok().body(updated);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUserCollect(){
        List<User> getUserAll = userService.getAllUser();
        return ResponseEntity.ok().body(getUserAll);
    }
}