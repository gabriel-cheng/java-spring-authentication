package com.example.authapplication.domain.user;

public record RequestUser(
    String name,
    int age,
    String email,
    String password
) { }
