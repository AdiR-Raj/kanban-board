package com.aditya.kanban.controller;

import com.aditya.kanban.dto.BoardColumnCreateDTO;
import com.aditya.kanban.dto.BoardColumnRenameDTO;
import com.aditya.kanban.dto.BoardColumnResponseDTO;
import com.aditya.kanban.model.BoardColumn;
import com.aditya.kanban.service.BoardColumnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/columns")
public class BoardColumnController {

    @Autowired
    private BoardColumnService boardColumnService;

    @PostMapping
    public BoardColumnResponseDTO createColumn(@Valid @RequestBody BoardColumnCreateDTO dto) {
        BoardColumn boardColumn = boardColumnService.createColumn(dto.getName(), dto.getPosition(), dto.getBoardId());

        return new BoardColumnResponseDTO(boardColumn.getId(), boardColumn.getName(), boardColumn.getPosition(), boardColumn.getBoard().getId());
    }

    @GetMapping
    public List<BoardColumnResponseDTO> getAllColumns() {
        return boardColumnService.getAllColumns()
                .stream()
                .map(c -> new BoardColumnResponseDTO(c.getId(), c.getName(), c.getPosition(), c.getBoard().getId()))
                .toList();
    }

    @PatchMapping("/{id}/rename")
    public BoardColumnResponseDTO renameColumn(@PathVariable Long id, @Valid @RequestBody BoardColumnRenameDTO dto) {
        BoardColumn boardColumn = boardColumnService.renameColumn(id, dto.getName());
        return new BoardColumnResponseDTO(boardColumn.getId(), boardColumn.getName(), boardColumn.getPosition(), boardColumn.getBoard().getId());
    }

    @DeleteMapping("/{id}")
    public void deleteColumn(@PathVariable Long id) {
        boardColumnService.deleteColumn(id);
    }
}