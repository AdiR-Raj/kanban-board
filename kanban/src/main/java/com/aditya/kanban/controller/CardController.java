package com.aditya.kanban.controller;

import com.aditya.kanban.dto.CardCreateDTO;
import com.aditya.kanban.dto.CardResponseDTO;
import com.aditya.kanban.model.Card;
import com.aditya.kanban.model.BoardColumn;
import com.aditya.kanban.repository.CardRepository;
import com.aditya.kanban.repository.BoardColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @PostMapping
    public CardResponseDTO createCard(@RequestBody CardCreateDTO dto) {
        BoardColumn column = boardColumnRepository.findById(dto.getColumnId())
                .orElseThrow(() -> new RuntimeException("Column not found: " + dto.getColumnId()));

        Card card = new Card();
        card.setTitle(dto.getTitle());
        card.setDescription(dto.getDescription());
        card.setPosition(dto.getPosition());
        card.setColumn(column);

        Card saved = cardRepository.save(card);

        return new CardResponseDTO(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getPosition(), saved.getColumn().getId());
    }

    @GetMapping
    public List<CardResponseDTO> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(c -> new CardResponseDTO(c.getId(), c.getTitle(), c.getDescription(), c.getPosition(), c.getColumn().getId()))
                .toList();
    }
}