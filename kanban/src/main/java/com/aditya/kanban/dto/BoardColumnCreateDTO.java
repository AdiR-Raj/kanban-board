package com.aditya.kanban.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BoardColumnCreateDTO {

    @NotBlank(message = "Column name is required")
    private String name;

    @NotNull(message = "Position is required")
    private Integer position;

    @NotNull(message = "Board ID is required")
    private Long boardId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position) { this.position = position; }

    public Long getBoardId() { return boardId; }
    public void setBoardId(Long boardId) { this.boardId = boardId; }
}