package com.apigateway.controller;

import com.apigateway.domain.Blog;
import com.apigateway.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */
@RestController
/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */
@RequestMapping(value = "/api/v1")
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * save a new blog
     */
    @PostMapping("/blog")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
        Blog savedBlog = blogService.saveBlog(blog);
        return new ResponseEntity<Blog>(savedBlog, HttpStatus.CREATED);
    }

    /**
     * retrieve all blogs
     */
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return new ResponseEntity<List<Blog>>((List<Blog>) blogService.getAllBlogs(), HttpStatus.OK);

    }

    /**
     * retrieve blog by id
     */
    @GetMapping("blog/{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable("blogId") int blogId) {
        return new ResponseEntity<Blog>(blogService.getBlogById(blogId), HttpStatus.FOUND);
    }

    /**
     * delete blog by id
     */
    @DeleteMapping("blog/{blogId}")
    public ResponseEntity<Blog> getBlogAfterDeleting(@PathVariable("blogId") int blogId) {
        return new ResponseEntity<Blog>(blogService.deleteBlog(blogId), HttpStatus.OK);
    }

    /**
     * update blog
     */
    @PutMapping("blog")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog) {
        Blog updatedBlog = blogService.updateBlog(blog);
        return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
    }
}