package com.example.animals_shop.controller;

import com.example.animals_shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private AnimalCategoryRepository animalCategoryRepository;

    @Autowired
    private AccessoriesCategoryRepository accessoriesCategoryRepository;

    @Autowired
    private FeedCategoryRepository feedCategoryRepository;


    @GetMapping("/")
    public String main(ModelMap map){
        map.addAttribute("animalCategories", animalCategoryRepository.findAll());
        map.addAttribute("accessoriesCategories", accessoriesCategoryRepository.findAll());
        map.addAttribute("feedCategories", feedCategoryRepository.findAll());
        return "index";
    }

    @GetMapping("/main/redirect")
    public String redirect(){
        return "redirect:/";
    }

}
