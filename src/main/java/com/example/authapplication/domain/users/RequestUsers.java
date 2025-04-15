package com.example.authapplication.domain.users;

public record RequestUsers(
    String name,
    int age,
    String email,
    String password,
    String role
) { }
