package com.aditya.kanban.controller;

import com.aditya.kanban.dto.BoardCreateDTO;
import com.aditya.kanban.dto.BoardResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aditya.kanban.service.BoardService;
import com.aditya.kanban.model.Board;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public BoardResponseDTO createBoard(@Valid @RequestBody BoardCreateDTO dto) {
        Board saved = boardService.createBoard(dto.getName(), dto.getOwnerId());
        return new BoardResponseDTO(saved.getId(), saved.getName(), saved.getOwner().getId());
    }

    @GetMapping
    public List<BoardResponseDTO> getAllBoards() {
        return boardService.getAllBoards()
                .stream()
                .map(b -> new BoardResponseDTO(b.getId(), b.getName(), b.getOwner().getId()))
                .toList();
    }
}