package com.crjoua.projerp.models.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String description;

    public Product() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product withId(Long id) {
        this.id = id;
        return this;
    }

    public Product withName(String name) {
        this.name = name;
        return this;
    }

    public Product withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name) && Objects.equals(this.description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description);
    }
}