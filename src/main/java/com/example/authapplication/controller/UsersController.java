package com.example.authapplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authapplication.domain.users.RequestUsers;
import com.example.authapplication.domain.users.UsersRepository;
import com.example.authapplication.domain.users.Users;
import com.example.authapplication.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> users = usersRepository.findAll();

            return ResponseEntity.status(HttpStatus.OK).body(users);

        } catch(Exception error) {
            System.out.println("Failed to get all users: " + error.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getOneUser(@PathVariable String id) throws ResourceNotFoundException {
        Users user = usersRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found!"));

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping()
    public ResponseEntity<String> registerNewUser(@RequestBody @Validated RequestUsers user) {
        try {
            Users newUser = new Users(user);

            usersRepository.save(newUser);

            return ResponseEntity.status(HttpStatus.CREATED).body("New user created successfully!");
        } catch(Exception error) {
            System.out.println("Failed to create a new user: " + error.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error ocurred. Please, try again later!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> changeUser(@RequestBody @Validated RequestUsers user, @PathVariable String id) throws ResourceNotFoundException {
        Users userFound = usersRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found!"));

        Users newUser = userFound;
        newUser.setName(user.name());
        newUser.setAge(user.age());
        newUser.setEmail(user.email());
        newUser.setPassword(user.password());

        usersRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOneUser(@PathVariable String id) throws ResourceNotFoundException {
         Users userFound = usersRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found!"));

         usersRepository.deleteById(userFound.getUser_id());

         return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
    }

    @PostMapping("/{login}")
    public ResponseEntity<Optional<Users>> userLogin(@RequestBody RequestUsers user) {
        Optional<Users> userFound = usersRepository.findByEmail(user.email());

        return ResponseEntity.status(HttpStatus.OK).body(userFound);
    }
}