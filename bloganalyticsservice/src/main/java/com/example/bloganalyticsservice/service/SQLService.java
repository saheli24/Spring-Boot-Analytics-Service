package com.example.bloganalyticsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate; // importing the template we use to connect to the database
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service // identifies that it's a service
public class SQLService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Method to get all blog posts
    public List<Map<String, Object>> getAllBlogPosts() {
        String sql = "SELECT * FROM theblog_post"; // this is where your SQL queries go
        return jdbcTemplate.queryForList(sql);
    }


    // Method to find all authors with their post count, including null usernames
    public List<Map<String, Object>> getAuthorsWithPostCount() {
        String sql = "SELECT u.username, COALESCE(COUNT(p.author_id), 0) AS post_count " +
        "FROM auth_user u " +
                "LEFT JOIN theblog_post p ON p.author_id = u.id " +
                "GROUP BY u.username " +
                "ORDER BY post_count DESC";

        return jdbcTemplate.queryForList(sql);
    }

    // Method to get the top 10 posts with the highest comment count
    public List<Map<String, Object>> getTopCommentedPosts() {
        String sql = "SELECT p.id, p.title, COUNT(c.id) AS comment_count " +
                "FROM theblog_post p " +
                "LEFT JOIN theblog_comment c ON p.id = c.post_id " +
                "GROUP BY p.id, p.title " +
                "ORDER BY comment_count DESC ";

        // Execute the query and return results
        return jdbcTemplate.queryForList(sql);
    }



}