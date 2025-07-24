package com.SalesApp.controller;

import com.SalesApp.model.Category;
import com.SalesApp.model.Customer;
import com.SalesApp.model.Product;
import com.SalesApp.repository.CategoryRepository;
import com.SalesApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class Category2Controller {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/category-lookup")
    public String showCategoryDropdown(Model model){
        model.addAttribute("category", categoryRepository.findAll());
        return "views/category/lookup";
    }

    @GetMapping("/api/category/{id}/products")
    @ResponseBody
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Product> products = productRepository.findByCategoryId(id);
        return ResponseEntity.ok(products);
    }
}
