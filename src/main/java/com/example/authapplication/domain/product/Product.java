package com.example.authapplication.domain.product;

import com.example.authapplication.domain.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="product")
@Entity(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="product_id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="product_id")
    private String product_id;

    private String name;

    private String description;

    private String brand;

    private String barcode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"products"})
    private Category category;

    public Product(RequestProduct requestProduct) {
        this.name = requestProduct.name();
        this.description = requestProduct.description();
        this.brand = requestProduct.brand();
        this.barcode = requestProduct.barcode();
    }

}
