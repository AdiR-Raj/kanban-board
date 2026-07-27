package com.aditya.kanban.controller;

import com.aditya.kanban.dto.UserCreateDTO;
import com.aditya.kanban.dto.UserResponseDTO;
import com.aditya.kanban.model.User;
import com.aditya.kanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserCreateDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); //hash this in the auth later

        User saved = userRepository.save(user);

        return new UserResponseDTO(saved.getId(), saved.getUsername(), saved.getEmail());
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getUsername(), u.getEmail()))
                .toList();
    }
}