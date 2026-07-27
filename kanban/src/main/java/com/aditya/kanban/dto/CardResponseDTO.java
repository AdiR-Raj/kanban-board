package com.aditya.kanban.dto;

public class CardResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Integer position;
    private Long columnId;

    //constructor that assigns values
    public CardResponseDTO(Long id, String title, String description, Integer position, Long columnId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.columnId = columnId;
        this.position = position;
    }

    //getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getColumnId() { return columnId; }
    public void setColumnId(Long columnId) { this.columnId = columnId; }
}
