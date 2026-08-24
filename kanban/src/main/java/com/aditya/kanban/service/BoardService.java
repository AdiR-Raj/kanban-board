package com.aditya.kanban.service;

import com.aditya.kanban.exception.ResourceNotFoundException;
import com.aditya.kanban.model.Board;
import com.aditya.kanban.model.User;
import com.aditya.kanban.repository.BoardRepository;
import com.aditya.kanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board createBoard(String name, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(("user not found with id: " + ownerId)));

        Board board = new Board();
        board.setName(name);
        board.setOwner(owner);

        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
}