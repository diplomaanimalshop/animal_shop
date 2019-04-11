package com.example.animals_shop.controller;

import com.example.animals_shop.model.Feed;
import com.example.animals_shop.model.FeedImage;
import com.example.animals_shop.repository.CategoryRepository;
import com.example.animals_shop.repository.FeedImageRepository;
import com.example.animals_shop.repository.FeedRepository;
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
@RequestMapping(value = "/feed")
public class FeedController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedImageRepository feedImageRepository;

    @GetMapping("/add")
    public String addFeedView(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "feedAdd";
    }

    @PostMapping("/add")
    public String addFeed(@ModelAttribute Feed feed, @RequestParam("picture") MultipartFile[] files) throws IOException {
        Feed savedFeed = feedRepository.save(feed);
        for (MultipartFile multipartFile : files) {
            FeedImage feedImage = new FeedImage();
            String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File("C:\\Users\\User\\IdeaProjects\\animals_shop\\images\\feed_files\\" + name);
            multipartFile.transferTo(file);
            feedImage.setPath(name);
            feedImage.setFeed(savedFeed);
            feedImageRepository.save(feedImage);
        }
        return "redirect:/";
    }

    @GetMapping("/byCategory")
    public String byCategory(@RequestParam("categoryId") int categoryId, ModelMap modelMap) {
        // modelMap.addAttribute("feeds", feedRepository.findFeedByCategory(categoryId));
        return "postsByCategory";
    }

    @GetMapping("/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("imageUrl") String imageUrl) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\User\\IdeaProjects\\animals_shop\\images\\feed_files\\" + imageUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
