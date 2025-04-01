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

import com.example.authapplication.domain.product.Product;
import com.example.authapplication.domain.product.ProductRepository;
import com.example.authapplication.domain.product.RequestProduct;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();

            return ResponseEntity.ok(products);
        } catch(Exception error) {
            System.out.println("Error to find products: " + error.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Collections.emptyList());
        }
    }

    @PostMapping
    public ResponseEntity<String> registerNewProduct(@RequestBody @Validated RequestProduct product) {
        try {
            Product newProduct = new Product();

            productRepository.save(newProduct);

            return ResponseEntity.ok("Product registered successfully!");
        } catch(Exception error) {
            System.out.println("Error to register a new product: " + error.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Failed to register a new product. Please, try again later!");
        }
    }
}
