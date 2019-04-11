package com.example.animals_shop.controller;

import com.example.animals_shop.model.Accessories;
import com.example.animals_shop.model.AccessoriesImage;
import com.example.animals_shop.repository.AccessoriesImageRepository;
import com.example.animals_shop.repository.AccessoriesRepository;
import com.example.animals_shop.repository.CategoryRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/accessories")
public class AccessoriesController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccessoriesRepository accessoriesRepository;

    @Autowired
    private AccessoriesImageRepository accessoriesImageRepository;

    @GetMapping("/add")
    public String addAccessoriesView(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "accessoriesAdd";
    }

    @PostMapping("/add")
    public String addAccessories(@ModelAttribute Accessories accessories, @RequestParam("picture") MultipartFile[] files) throws IOException {
        Accessories savedAccessories = accessoriesRepository.save(accessories);
        for (MultipartFile multipartFile : files) {
            AccessoriesImage accessoriesImage = new AccessoriesImage();
            String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File("C:\\Users\\User\\IdeaProjects\\animals_shop\\images\\accessories_files\\" + name);
            multipartFile.transferTo(file);
            accessoriesImage.setPath(name);
            accessoriesImage.setAccessories(savedAccessories);
            accessoriesImageRepository.save(accessoriesImage);
        }
        return "redirect:/";
    }

    @GetMapping("/byCategory")
    public String byCategory(@RequestParam("categoryId") int categoryId, ModelMap modelMap) {
        // modelMap.addAttribute("animals", animalRepository.findAnimalByCategory(categoryId));
        return "postsByCategory";
    }

    @GetMapping("/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("imageUrl") String imageUrl) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\User\\IdeaProjects\\animals_shop\\images\\accessories_files\\" + imageUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
