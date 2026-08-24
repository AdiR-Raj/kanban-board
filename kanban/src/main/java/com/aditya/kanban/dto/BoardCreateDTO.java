package com.aditya.kanban.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BoardCreateDTO {

    @NotBlank(message = "Board name is required")
    private String name;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}