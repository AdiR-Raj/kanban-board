package com.aditya.kanban.controller;

import com.aditya.kanban.dto.UserCreateDTO;
import com.aditya.kanban.dto.UserResponseDTO;
import com.aditya.kanban.model.User;
import com.aditya.kanban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserCreateDTO dto) {
        User saved = userService.createUser(dto.getUsername(), dto.getEmail(), dto.getPassword());
        return new UserResponseDTO(saved.getId(),saved.getUsername(),saved.getEmail());
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(u -> new UserResponseDTO(u.getId(),u.getUsername(),u.getEmail()))
                .toList();
    }
}