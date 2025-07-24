package com.SalesApp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@JsonIgnoreProperties({"sale"})
@Entity
@Table(name = "saleitem")
public class SaleItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)

    private Sale sale;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


    private int quantity;


    private double Price;


    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    public Sale getSale() { return sale; }
    public void setSale(Sale sale) { this.sale = sale; }


    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }


    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }


    public double getUnitPrice() { return Price; }
    public void setUnitPrice(double unitPrice) { this.Price = Price; } }
