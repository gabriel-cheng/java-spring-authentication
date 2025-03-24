package com.example.authapplication.domain.product;

public record RequestProduct(
    String name,
    String description,
    String brand,
    String barcode
) { }
