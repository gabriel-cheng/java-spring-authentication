package com.example.authapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authapplication.domain.user.UserRepository;
import com.example.authapplication.domain.user.Users;
import com.example.authapplication.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() throws ResourceNotFoundException {
        try {
            List<Users> users = userRepository.findAll();

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);

        } catch(Exception error) {
            System.out.println("An unexpected error ocurred :: " + error.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
