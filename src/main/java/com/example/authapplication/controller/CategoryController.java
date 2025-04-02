package com.example.authapplication.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authapplication.domain.category.Category;
import com.example.authapplication.domain.category.CategoryRepository;
import com.example.authapplication.domain.category.RequestCategory;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> allCategories = categoryRepository.findAll();

            return ResponseEntity.ok(allCategories);
        } catch(Exception error) {
            System.out.println("Internal server error, please try again later. " + error.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Collections.emptyList());
        }
    }

    @PostMapping
    public ResponseEntity<String> registerNewCategory(@RequestBody @Validated RequestCategory category) {
        try {
            Category newCategory = new Category(category);

            categoryRepository.save(newCategory);

            return ResponseEntity.ok("Category created successfully!");
        } catch(Exception error) {
            System.out.println("Error to register that category: " + error.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Internal server error, please try again later!");
        }
    }
}
