package com.aditya.kanban.dto;

public class CardCreateDTO {
    private String title;
    private String description;
    private Integer position;
    private Long columnId;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }

    public Long getColumnId() { return columnId; }
    public void setColumnId(Long columnId) { this.columnId = columnId; }
}