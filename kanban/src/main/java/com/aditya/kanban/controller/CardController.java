package com.aditya.kanban.controller;

import com.aditya.kanban.dto.CardCreateDTO;
import com.aditya.kanban.dto.CardResponseDTO;
import com.aditya.kanban.model.Card;
import com.aditya.kanban.service.CardService;
import com.aditya.kanban.dto.CardMoveDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public CardResponseDTO createCard(@Valid @RequestBody CardCreateDTO dto) {
        Card card = cardService.createCard(dto.getTitle(), dto.getDescription(), dto.getPosition(), dto.getColumnId());

        return new CardResponseDTO(card.getId(), card.getTitle(), card.getDescription(), card.getPosition(), card.getColumn().getId());
    }

    @PatchMapping("/{id}/move")
    public CardResponseDTO moveCard(@PathVariable Long id, @Valid @RequestBody CardMoveDTO dto) {
        Card card = cardService.moveCard(id, dto.getColumnId(), dto.getPosition());
        return new CardResponseDTO(card.getId(), card.getTitle(), card.getDescription(), card.getPosition(), card.getColumn().getId());
    }

    @GetMapping
    public List<CardResponseDTO> getAllCards() {
        return cardService.getAllCards()
                .stream()
                .map(c -> new CardResponseDTO(c.getId(), c.getTitle(), c.getDescription(), c.getPosition(), c.getColumn().getId()))
                .toList();
    }
}