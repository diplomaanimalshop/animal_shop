package com.example.animals_shop.controller;

import com.example.animals_shop.model.Accessories;
import com.example.animals_shop.model.AccessoriesImage;
import com.example.animals_shop.model.User;
import com.example.animals_shop.repository.AccessoriesCategoryRepository;
import com.example.animals_shop.repository.AccessoriesImageRepository;
import com.example.animals_shop.repository.AccessoriesRepository;
import com.example.animals_shop.repository.UserRepository;
import com.example.animals_shop.security.SpringUser;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Controller
public class AccessoriesController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessoriesCategoryRepository accessoriesCategoryRepository;

    @Autowired
    private AccessoriesImageRepository accessoriesImageRepository;

    @Autowired
    private AccessoriesRepository accessoriesRepository;

    @PostMapping("/addAccessories")
    public String addAccessories(@ModelAttribute Accessories accessories, @RequestParam("picture") MultipartFile[] files, @AuthenticationPrincipal SpringUser springUser, ModelMap map) throws IOException {
        Accessories savedAccessories = accessoriesRepository.save(accessories);
        for (MultipartFile multipartFile : files) {
            AccessoriesImage accessoriesImage = new AccessoriesImage();
            String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File("C:\\Users\\Mariam\\IdeaProjects\\new\\animal_shop\\images\\accessories_files\\" + name);
            multipartFile.transferTo(file);
            accessoriesImage.setPath(name);
            accessories.setUser(springUser.getUser());
            accessories.setCreatedDate(new Date());
            accessoriesImage.setAccessories(savedAccessories);
            accessoriesImageRepository.save(accessoriesImage);
        }
        return "redirect:/addAccessoryPage";
    }

    @PostMapping("/user/addAccessories")
    public String userAddAccessories(@ModelAttribute Accessories accessories, @RequestParam("picture") MultipartFile[] files, @AuthenticationPrincipal SpringUser springUser, ModelMap map) throws IOException {
        Accessories savedAccessories = accessoriesRepository.save(accessories);
        for (MultipartFile multipartFile : files) {
            AccessoriesImage accessoriesImage = new AccessoriesImage();
            String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File("C:\\Users\\Mariam\\IdeaProjects\\new\\animal_shop\\images\\accessories_files\\" + name);
            multipartFile.transferTo(file);
            accessoriesImage.setPath(name);
            accessories.setUser(springUser.getUser());
            accessories.setCreatedDate(new Date());
            accessoriesImage.setAccessories(savedAccessories);
            accessoriesImageRepository.save(accessoriesImage);
        }
        return "redirect:/user/addAccessoryPage";
    }

    @GetMapping("/accessories/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("imageUrl") String imageUrl) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\Mariam\\IdeaProjects\\new\\animal_shop\\images\\accessories_files\\" + imageUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/addAccessoryPage")
    public String addAccessoryPage(ModelMap map, @AuthenticationPrincipal SpringUser springUser) {
        map.addAttribute("accessoryCategory", accessoriesCategoryRepository.findAll());
        map.addAttribute("user", springUser.getUser());
        return "addAccessory";
    }

    @GetMapping("/user/addAccessoryPage")
    public String userAddAccessoryPage(ModelMap map, @AuthenticationPrincipal SpringUser springUser) {
        map.addAttribute("accessoryCategory", accessoriesCategoryRepository.findAll());
        map.addAttribute("user", springUser.getUser());
        return "userAddAccessory";
    }

    @GetMapping("/allAccessories")
    public String allAccessories(ModelMap map, @AuthenticationPrincipal SpringUser springUser) {
        map.addAttribute("user", springUser.getUser());
        map.addAttribute("accessories", accessoriesRepository.findAll());
        return "allAccessories";
    }

    @GetMapping("/accessory/delete")
    public String deleteAccessory(int id) {
        accessoriesRepository.deleteById(id);
        return "redirect:/allAccessories";
    }

    @GetMapping("/user/allAccessories")
    public String userAllAccessories(ModelMap map, @AuthenticationPrincipal SpringUser springUser) {
        map.addAttribute("user", springUser.getUser());
        map.addAttribute("accessories", accessoriesRepository.findAllByUserId(springUser.getUser().getId()));
        return "userAllAccessories";
    }

    @GetMapping("/user/accessory/delete")
    public String userDeleteAccessory(int id) {
        accessoriesRepository.deleteById(id);
        return "redirect:/user/allAccessories";
    }

}