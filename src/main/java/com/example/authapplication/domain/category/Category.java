package com.example.authapplication.domain.category;

import java.util.ArrayList;
import java.util.List;

import com.example.authapplication.domain.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="category")
@Entity(name="category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="category_id")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="category_id")
    private String category_id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Category(RequestCategory requestCategory) {
        this.name = requestCategory.name();
        this.description = requestCategory.description();
    }
}
