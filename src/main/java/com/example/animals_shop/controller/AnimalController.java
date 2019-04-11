package com.example.animals_shop.controller;

import com.example.animals_shop.model.Animal;
import com.example.animals_shop.model.AnimalImage;
import com.example.animals_shop.repository.AnimalRepository;
import com.example.animals_shop.repository.CategoryRepository;
import com.example.animals_shop.repository.AnimalImageRepository;
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
@RequestMapping(value = "/animal")
public class AnimalController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalImageRepository animalImageRepository;

    @GetMapping("/add")
    public String addAnimalView(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "animalAdd";
    }

    @PostMapping("/add")
    public String addAnimal(@ModelAttribute Animal animal,@RequestParam("picture") MultipartFile[] files) throws IOException {
        Animal savedAnimal = animalRepository.save(animal);
        for (MultipartFile multipartFile : files) {
            AnimalImage animalImage = new AnimalImage();
            String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File("C:\\Users\\User\\IdeaProjects\\animals_shop\\images\\animal_files\\" + name);
            multipartFile.transferTo(file);
            animalImage.setPath(name);
            animalImage.setAnimal(savedAnimal);
            animalImageRepository.save(animalImage);
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
        InputStream in = new FileInputStream("C:\\Users\\User\\IdeaProjects\\animals_shop\\images\\animal_files\\" + imageUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
