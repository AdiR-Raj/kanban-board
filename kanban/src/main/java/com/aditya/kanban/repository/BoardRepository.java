package com.aditya.kanban.repository;

import com.aditya.kanban.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>{
}
