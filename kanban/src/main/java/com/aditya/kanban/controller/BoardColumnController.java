package com.aditya.kanban.controller;

import com.aditya.kanban.dto.BoardColumnCreateDTO;
import com.aditya.kanban.dto.BoardColumnResponseDTO;
import com.aditya.kanban.model.Board;
import com.aditya.kanban.model.BoardColumn;
import com.aditya.kanban.repository.BoardRepository;
import com.aditya.kanban.repository.BoardColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/columns")
public class BoardColumnController {

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Autowired
    private BoardRepository boardRepository;

    @PostMapping
    public BoardColumnResponseDTO createColumn(@RequestBody BoardColumnCreateDTO dto) {
        Board board = boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new RuntimeException("Board not found with id: " + dto.getBoardId()));

        BoardColumn column = new BoardColumn();
        column.setName(dto.getName());
        column.setPosition(dto.getPosition());
        column.setBoard(board);

        BoardColumn saved = boardColumnRepository.save(column);

        return new BoardColumnResponseDTO(saved.getId(), saved.getName(), saved.getPosition(), saved.getBoard().getId());
    }

    @GetMapping
    public List<BoardColumnResponseDTO> getAllColumns() {
        return boardColumnRepository.findAll()
                .stream()
                .map(c -> new BoardColumnResponseDTO(c.getId(), c.getName(), c.getPosition(), c.getBoard().getId()))
                .toList();
    }
}