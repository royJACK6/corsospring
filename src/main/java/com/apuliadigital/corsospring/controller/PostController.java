package com.apuliadigital.corsospring.controller;

import com.apuliadigital.corsospring.model.Post;
import com.apuliadigital.corsospring.model.User;
import com.apuliadigital.corsospring.service.PostService;
import com.apuliadigital.corsospring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    
    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }
    
    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostByIdPath(@PathVariable int id){
        Post post = postService.getPostById(id);
        if(post == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping("/user/{user_id}")
    public ResponseEntity<Post> createPost(@RequestBody @Valid Post post, @PathVariable int user_id){
        User user = userService.getUserById(user_id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        post.setUser(user);
        Post savedPost = postService.createPost(post);
        return ResponseEntity.ok(savedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id){
        if (postService.deletePostById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody Post updatePost){
        Post post = postService.updatePost(id, updatePost);
        if (post == null){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(post);
    }
}
