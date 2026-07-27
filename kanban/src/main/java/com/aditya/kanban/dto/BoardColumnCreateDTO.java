package com.aditya.kanban.dto;

public class BoardColumnCreateDTO {
    private String name;
    private Integer position;
    private Long boardId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }

    public Long getBoardId() { return boardId; }
    public void setBoardId(Long boardId) { this.boardId = boardId; }
}