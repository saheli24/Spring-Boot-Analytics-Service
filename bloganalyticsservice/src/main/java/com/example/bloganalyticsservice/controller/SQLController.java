package com.example.bloganalyticsservice.controller;

import com.example.bloganalyticsservice.service.SQLService; // Import your service to access it
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; // Don't forget the semicolon here

@RestController
@RequestMapping // Specify a base URI path for this controller
public class SQLController {

    @Autowired
    private SQLService sqlService;

    @GetMapping("/all")
    public List<Map<String, Object>> getAllBlogPosts() {
        return sqlService.getAllBlogPosts();
    }

    // Endpoint to get authors with their post counts
    @GetMapping("/authors-with-posts")
    public List<Map<String, Object>> getAuthorsWithPostCount() {
        return sqlService.getAuthorsWithPostCount();
    }
    // Endpoint to get the top 10 most commented posts
    @GetMapping("/top-commented-posts")
    public List<Map<String, Object>> getTopCommentedPosts() {
        return sqlService.getTopCommentedPosts();
    }
}
