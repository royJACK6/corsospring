package com.apuliadigital.corsospring.dto;

import java.util.List;

public class UserResponseDTO {
    private int id;
    private String name;
    private String email;
    private  int age;
    private List<PostResponseDTO> posts;

    public UserResponseDTO() {
    }
    public UserResponseDTO(int id, String name, String email, int age, List<PostResponseDTO> posts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<PostResponseDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponseDTO> posts) {
        this.posts = posts;
    }
}
