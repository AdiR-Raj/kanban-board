package com.aditya.kanban.service;

import com.aditya.kanban.model.Board;
import com.aditya.kanban.model.BoardColumn;
import com.aditya.kanban.repository.BoardColumnRepository;
import com.aditya.kanban.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardColumnService {

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Autowired
    private BoardRepository boardRepository;

    public BoardColumn createColumn(String name, Integer position, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found with id: + " + boardId));


        BoardColumn boardColumn = new BoardColumn();
        boardColumn.setName(name);
        boardColumn.setPosition(position);
        boardColumn.setBoard(board);

        return boardColumnRepository.save(boardColumn);
    }

    public List<BoardColumn> getAllColumns() {
        return boardColumnRepository.findAll();
    }
}
