package com.example.authapplication.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authapplication.domain.category.Category;
import com.example.authapplication.domain.category.CategoryRepository;
import com.example.authapplication.domain.product.Product;
import com.example.authapplication.domain.product.ProductRepository;
import com.example.authapplication.domain.product.RequestProduct;
import com.example.authapplication.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable String id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found!"));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    @PostMapping
    public ResponseEntity<String> registerNewProduct(@RequestBody @Validated RequestProduct product) {
        try {
            Category categoryFound = categoryRepository.findById(product.category_id())
            .orElseThrow(() -> new ResourceNotFoundException("Category " + product.category_id() + " not found!"));

            Product newProduct = new Product(product);
            newProduct.setCategory(categoryFound);

            productRepository.save(newProduct);

            return ResponseEntity.ok("Product registered successfully!");
        } catch(Exception error) {
            System.out.println("Error to register a new product: " + error.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Failed to register a new product. Please, try again later!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> changeOneProduct(@RequestBody @Validated RequestProduct product, @PathVariable String id) throws ResourceNotFoundException {
        Product productFound = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found!"));
        Category categoryFound = categoryRepository.findById(product.category_id())
        .orElseThrow(() -> new ResourceNotFoundException("Category " + product.category_id() + " not found!"));

        Product newProduct = productFound;
        newProduct.setName(product.name());
        newProduct.setDescription(product.description());
        newProduct.setBrand(product.brand());
        newProduct.setBarcode(product.barcode());
        newProduct.setCategory(categoryFound);

        productRepository.save(newProduct);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newProduct);
    }
}
