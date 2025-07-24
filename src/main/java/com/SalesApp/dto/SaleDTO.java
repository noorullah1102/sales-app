package com.SalesApp.dto;


import java.time.LocalDate;
import java.util.List;


public class SaleDTO {
    private Integer customerId;
    private LocalDate saleDate;
    private List<SaleItemDTO> items;


    // Getters and Setters
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }


    public LocalDate getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDate saleDate) { this.saleDate = saleDate; }


    public List<SaleItemDTO> getItems() { return items; }
    public void setItems(List<SaleItemDTO> items) { this.items = items; }
}
