package com.apuliadigital.corsospring.service;

import com.apuliadigital.corsospring.dto.PostResponseDTO;
import com.apuliadigital.corsospring.dto.UserDTO;
import com.apuliadigital.corsospring.dto.UserResponseDTO;
import com.apuliadigital.corsospring.model.User;
import com.apuliadigital.corsospring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll().stream().map(user -> {
            List<PostResponseDTO> posts = user.getPosts().stream()
                    .map(post -> new PostResponseDTO(
                            post.getId(),
                            post.getTitle(),
                            post.getContent()))
                        .toList();
            return new UserResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getAge(),
                    posts
            );
        }).toList();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(UserDTO userDTO){
        User user= new User(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getAge()
        );
        return userRepository.save(user);
    }

    public boolean deleteUserById(int id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User updateUser(int id, User updateUser){
        if (userRepository.existsById(id)) {
            updateUser.setId(id);
            return userRepository.save(updateUser);
        }
        return null;
    }
}
