package com.aditya.kanban.controller;

import com.aditya.kanban.dto.LoginRequestDTO;
import com.aditya.kanban.dto.LoginResponseDTO;
import com.aditya.kanban.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
        String token = userService.login(dto.getUsername(), dto.getPassword());
        return new LoginResponseDTO(token);
    }
}