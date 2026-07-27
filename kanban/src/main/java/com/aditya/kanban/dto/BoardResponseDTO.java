package com.aditya.kanban.dto;

public class BoardResponseDTO {
    private Long id;
    private String name;
    private Long ownerId;

    public BoardResponseDTO(Long id, String name, Long ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    public Long getId() { return id; }
    public String getName() { return name; }

    public Long getOwnerId() { return ownerId; }
}