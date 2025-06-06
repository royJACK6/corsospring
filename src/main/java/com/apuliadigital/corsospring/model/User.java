package com.apuliadigital.corsospring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank (message = "Il nome è obbligatorio")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "La mail è obbligatoria")
    @Email(message = "Formato non idoneo")
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private  int age;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(int id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
