package com.SalesApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "sale")
public class Sale {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name="sale_date")
    private LocalDate saleDate;


    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)

    private List<SaleItem> items;


    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }


    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }


    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }


    public List<SaleItem> getItems() { return items; }
    public void setItems(List<SaleItem> items) { this.items = items; }
}
