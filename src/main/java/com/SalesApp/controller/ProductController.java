package com.SalesApp.controller;


import com.SalesApp.model.Product;
import com.SalesApp.model.Category;
import com.SalesApp.repository.ProductRepository;
import com.SalesApp.repository.CategoryRepository;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class ProductController {


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/products")
    public String listProducts(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5")int size) {

        Page<Product> productPage = productRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("productPage", productPage);
        return "views/product/index";
    }


    @GetMapping("/products/create")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "views/product/create";
    }


    @PostMapping("/products")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult result,
                              Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("categories", categoryRepository.findAll());
            return "views/product/create";
        }


        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products-search")
    public String productSearchPage(){
        return "/views/product/search";
    }
    @GetMapping("/api/products/search")
    @ResponseBody
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query){
        List<Product> results = productRepository.findByNameContainingIgnoreCase(query);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/products/{id}/edit")
    public String editProductForm(@PathVariable int id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        return "views/product/edit";
    }

    @PostMapping("/products/{id}/edit")
    public String updateProduct(@PathVariable int id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "views/product/edit";
        }

        // Ensure correct ID
        product.setId(id);
        productRepository.save(product);

        return "redirect:/products";
    }


}
