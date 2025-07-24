package com.SalesApp.controller;

import com.SalesApp.model.Category;
import com.SalesApp.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public String viewCategory(Model model)
    {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "views/category/categoryIndex";
    }
    @GetMapping("/category/new")
    public String showAddForm(Model model)
    {
        model.addAttribute("categories", new Category());
        return "/views/category/form";
    }
    @PostMapping("/category")
    public String saveCategory(@Valid @ModelAttribute("categories") Category category,
                               BindingResult result, Model model){
    if(result.hasErrors()) {
        return "views/category/form";
    }
        categoryRepository.save(category);
        return "redirect:/category";
    }
}
