package com.SalesApp.viewmodel;


import java.util.List;


public class SaleRequest {
    private int customerId;
    private String saleDate;
    private List<SaleItemRequest> items;


    public int getCustomerId() {
        return customerId;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


    public String getSaleDate() {
        return saleDate;
    }


    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }


    public List<SaleItemRequest> getItems() {
        return items;
    }


    public void setItems(List<SaleItemRequest> items) {
        this.items = items;
    }
}
