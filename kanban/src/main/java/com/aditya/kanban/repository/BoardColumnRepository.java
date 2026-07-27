package com.aditya.kanban.repository;

import com.aditya.kanban.model.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
}