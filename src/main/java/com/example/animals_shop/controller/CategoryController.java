package com.example.animals_shop.controller;

import com.example.animals_shop.model.Category;
import com.example.animals_shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/add")
    public String addCategoryView(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return ("categoryAdd");
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/";
    }
}
