package com.SalesApp.repository;


import com.SalesApp.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaleRepository extends JpaRepository<Sale, Integer> {
    // You can define custom queries here if needed later
}
