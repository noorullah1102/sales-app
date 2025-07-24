package com.SalesApp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "Product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "Product name is required")
    private String name;


    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private double price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category is required")
    private Category category;


    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }


    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
