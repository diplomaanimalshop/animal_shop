package com.example.animals_shop.controller;


import com.example.animals_shop.model.Feed;
import com.example.animals_shop.model.FeedImage;
import com.example.animals_shop.repository.FeedCategoryRepository;
import com.example.animals_shop.repository.FeedImageRepository;
import com.example.animals_shop.repository.FeedRepository;
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
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedImageRepository feedImageRepository;

    @Autowired
    private FeedCategoryRepository feedCategoryRepository;


    @PostMapping("/addFeed")
    public String addFeed(@ModelAttribute Feed feed, @RequestParam("picture") MultipartFile[] files, @AuthenticationPrincipal SpringUser springUser, ModelMap map) throws IOException {
        Feed savedFeed = feedRepository.save(feed);
        for (MultipartFile multipartFile : files) {
            FeedImage feedImage = new FeedImage();
            String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File("C:\\Users\\Mariam\\IdeaProjects\\new\\animal_shop\\images\\feed_files\\" + name);
            multipartFile.transferTo(file);
            feedImage.setPath(name);
            feed.setUser(springUser.getUser());
            feed.setCreatedDate(new Date());
            feedImage.setFeed(savedFeed);
            feedImageRepository.save(feedImage);
        }
        return "redirect:/addFeedPage";
    }

    @PostMapping("/user/addFeed")
    public String userAddFeed(@ModelAttribute Feed feed, @RequestParam("picture") MultipartFile[] files, @AuthenticationPrincipal SpringUser springUser, ModelMap map) throws IOException {
        Feed savedFeed = feedRepository.save(feed);
        for (MultipartFile multipartFile : files) {
            FeedImage feedImage = new FeedImage();
            String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File("C:\\Users\\Mariam\\IdeaProjects\\new\\animal_shop\\images\\feed_files\\" + name);
            multipartFile.transferTo(file);
            feedImage.setPath(name);
            feed.setUser(springUser.getUser());
            feed.setCreatedDate(new Date());
            feedImage.setFeed(savedFeed);
            feedImageRepository.save(feedImage);
        }
        return "redirect:/user/addFeedPage";
    }



    @GetMapping("/feed/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("imageUrl") String imageUrl) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\Mariam\\IdeaProjects\\new\\animal_shop\\images\\feed_files\\" + imageUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }


    @GetMapping("/addFeedPage")
    public String addFeedPage(ModelMap map, @AuthenticationPrincipal SpringUser springUser){
        map.addAttribute("feedCategory", feedCategoryRepository.findAll());
        map.addAttribute("user", springUser.getUser());
        return "addFeed";
    }

    @GetMapping("/user/addFeedPage")
    public String userAddFeedPage(ModelMap map, @AuthenticationPrincipal SpringUser springUser){
        map.addAttribute("feedCategory", feedCategoryRepository.findAll());
        map.addAttribute("user", springUser.getUser());
        return "userAddFeed";
    }

    @GetMapping("/allFeeds")
    public String allFeeds(ModelMap map, @AuthenticationPrincipal SpringUser springUser){
        map.addAttribute("user", springUser.getUser());
        map.addAttribute("feeds",feedRepository.findAll());
        return "allFeeds";
    }

    @GetMapping("/feed/delete")
    public String deleteFeed(int id){
        feedRepository.deleteById(id);
        return "redirect:/allFeeds";
    }
    @GetMapping("/user/allFeeds")
    public String userAllFeeds(ModelMap map, @AuthenticationPrincipal SpringUser springUser){
        map.addAttribute("user", springUser.getUser());
        map.addAttribute("feeds",feedRepository.findAllByUserId(springUser.getUser().getId()));
        return "userAllFeeds";
    }

    @GetMapping("/user/feed/delete")
    public String userDeleteFeed(int id){
        feedRepository.deleteById(id);
        return "redirect:/user/allFeeds";
    }
}
