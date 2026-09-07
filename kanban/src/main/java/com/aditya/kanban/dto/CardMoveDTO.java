package com.aditya.kanban.dto;

import jakarta.validation.constraints.NotNull;

public class CardMoveDTO {

    @NotNull
    private Long columnId;

    @NotNull
    private Integer position;

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}