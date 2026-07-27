package com.aditya.kanban.controller;

import com.aditya.kanban.dto.BoardCreateDTO;
import com.aditya.kanban.dto.BoardResponseDTO;
import com.aditya.kanban.model.Board;
import com.aditya.kanban.model.User;
import com.aditya.kanban.repository.BoardRepository;
import com.aditya.kanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public BoardResponseDTO createBoard(@RequestBody BoardCreateDTO dto) {
        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getOwnerId()));

        Board board = new Board();
        board.setName(dto.getName());
        board.setOwner(owner);

        Board saved = boardRepository.save(board);

        return new BoardResponseDTO(saved.getId(), saved.getName(), saved.getOwner().getId());
    }

    @GetMapping
    public List<BoardResponseDTO> getAllBoards() {
        return boardRepository.findAll()
                .stream()
                .map(b -> new BoardResponseDTO(b.getId(), b.getName(), b.getOwner().getId()))
                .toList();
    }
}