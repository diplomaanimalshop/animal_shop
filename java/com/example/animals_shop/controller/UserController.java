package com.example.animals_shop.controller;

import com.example.animals_shop.model.User;
import com.example.animals_shop.repository.UserRepository;
import com.example.animals_shop.security.SpringUser;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
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

@Controller
public class UserController {

    @GetMapping("/user")
    public String userPage() {
        return "redirect:/user/addAnimalPage";
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/addUserPage")
    public String addUserPage(ModelMap map, @AuthenticationPrincipal SpringUser springUser) {
        map.addAttribute("user", springUser.getUser());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user, @RequestParam("picture") MultipartFile file) throws IOException {
        String path = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File("C:\\Users\\Mariam\\IdeaProjects\\new\\animal_shop\\images\\" + path);
        file.transferTo(image);
        user.setAvatar(path);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "addUser";
    }

    @GetMapping("/user/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("picture") String picUrl) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\Mariam\\IdeaProjects\\new\\animal_shop\\images\\" + picUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping("/allUsers")
    public String allUsers(ModelMap map, @AuthenticationPrincipal SpringUser springUser) {
        map.addAttribute("users", userRepository.findAll());
        map.addAttribute("user", springUser.getUser());
        return "allUsers";
    }

    @GetMapping("/delete")
    public String deleteUser(int id){
        userRepository.deleteById(id);
        return "redirect:/allUsers";
    }
}
