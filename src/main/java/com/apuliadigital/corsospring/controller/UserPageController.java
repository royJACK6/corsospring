package com.apuliadigital.corsospring.controller;

import com.apuliadigital.corsospring.dto.UserDTO;
import com.apuliadigital.corsospring.dto.UserResponseDTO;
import com.apuliadigital.corsospring.model.User;
import com.apuliadigital.corsospring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserPageController {
    private final UserService userService;

    private UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String List(Model model) {
        List<UserResponseDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/users";
        }
        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        return "user-detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new UserDTO());
        return "user-add";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        return "redirect:/users/" + user.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute User user, @PathVariable int id) {
        userService.updateUser(id, user);
        return "redirect:/users/" + user.getId();
    }
}
