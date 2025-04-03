package com.example.authapplication.domain.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="users")
@Entity(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="user_id")
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="user_id")
    private String user_id;

    private String name;

    private int age;

    private String email;

    private String password;

    public Users(RequestUsers requestUser) {
        this.name = requestUser.name();
        this.age = requestUser.age();
        this.email = requestUser.email();
        this.password = requestUser.password();
    }

}
