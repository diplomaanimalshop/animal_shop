package com.example.animals_shop.controller;

import com.example.animals_shop.repository.CategoryRepository;
import com.example.animals_shop.repository.AnimalRepository;
import com.example.animals_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String main(ModelMap modelMap) {
//        List <User> all = userRepository.findAll();
//        List <Post> allPosts = animalRepository.findAll();
//        modelMap.addAttribute("users", all);
//        modelMap.addAttribute("posts", allPosts);
//        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }
}
