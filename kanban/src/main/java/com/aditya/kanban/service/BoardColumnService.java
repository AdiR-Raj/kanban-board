package com.aditya.kanban.service;

import com.aditya.kanban.exception.ResourceNotFoundException;
import com.aditya.kanban.model.Board;
import com.aditya.kanban.model.BoardColumn;
import com.aditya.kanban.repository.BoardColumnRepository;
import com.aditya.kanban.repository.BoardRepository;
import com.aditya.kanban.dto.BoardColumnResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardColumnService {

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public BoardColumn createColumn(String name, Integer position, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: + " + boardId));

        BoardColumn boardColumn = new BoardColumn();
        boardColumn.setName(name);
        boardColumn.setPosition(position);
        boardColumn.setBoard(board);

        BoardColumn savedColumn = boardColumnRepository.save(boardColumn);

        messagingTemplate.convertAndSend(
                "/topic/board/" + boardId,
                new BoardColumnResponseDTO(savedColumn.getId(), savedColumn.getName(), savedColumn.getPosition(), boardId)
        );

        return savedColumn;
    }

    public List<BoardColumn> getAllColumns() {
        return boardColumnRepository.findAll();
    }

    public BoardColumn renameColumn(Long columnId, String newName) {
        BoardColumn column = boardColumnRepository.findById(columnId)
                .orElseThrow(() -> new ResourceNotFoundException("Column not found with id: " + columnId));

        column.setName(newName);
        BoardColumn savedColumn = boardColumnRepository.save(column);

        messagingTemplate.convertAndSend(
                "/topic/board/" + savedColumn.getBoard().getId(),
                new BoardColumnResponseDTO(savedColumn.getId(), savedColumn.getName(), savedColumn.getPosition(), savedColumn.getBoard().getId())
        );

        return savedColumn;
    }

    public void deleteColumn(Long columnId) {
        BoardColumn column = boardColumnRepository.findById(columnId)
                .orElseThrow(() -> new ResourceNotFoundException("Column not found with id: " + columnId));

        Long boardId = column.getBoard().getId();

        boardColumnRepository.delete(column);

        messagingTemplate.convertAndSend(
                "/topic/board/" + boardId,
                "COLUMN_DELETED:" + columnId
        );
    }
}