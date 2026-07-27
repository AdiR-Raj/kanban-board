package com.aditya.kanban.dto;

public class BoardCreateDTO {
    private String name;
    private Long ownerId;

    public String getName() { return name ;}
    public void setName(String name) { this.name = name; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}