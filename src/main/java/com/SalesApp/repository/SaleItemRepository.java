package com.SalesApp.repository;


import com.SalesApp.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleItemRepository extends JpaRepository<SaleItem, Integer> {
    // Optional: methods like findBySaleId(int saleId) if needed
}
