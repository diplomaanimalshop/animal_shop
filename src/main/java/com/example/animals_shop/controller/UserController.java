package com.example.animals_shop.controller;

import com.example.animals_shop.model.User;
import com.example.animals_shop.repository.UserRepository;
import com.example.animals_shop.service.EmailService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/home";
    }

    @PostMapping("/user/add")
    public String addUserPost(@ModelAttribute User user, @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File("C:\\Users\\User\\IdeaProjects\\animals_shop\\images\\avatars\\" + name);
        multipartFile.transferTo(file);
        user.setAvatar(name);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        emailService.sendSimpleMessage(user.getEmail(),
                "Hi dear User.",
                "Congrats!You are registered successfully");

        return "redirect:/login";
    }

    @GetMapping("/user/add")
    public String addUserGet() {
        return "registration";
    }

    @GetMapping("/user/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("avatar") String picUrl) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\User\\IdeaProjects\\animals_shop\\images\\avatars\\" + picUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
