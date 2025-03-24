package com.example.authapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping
    public ResponseEntity<String> getAllProducts() {
        return new ResponseEntity<>("Hello, world!", HttpStatus.OK);
    }
}
