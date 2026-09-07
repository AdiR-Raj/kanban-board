package com.aditya.kanban.service;

import com.aditya.kanban.dto.CardResponseDTO;
import com.aditya.kanban.exception.ResourceNotFoundException;
import com.aditya.kanban.model.Card;
import com.aditya.kanban.model.BoardColumn;
import com.aditya.kanban.repository.CardRepository;
import com.aditya.kanban.repository.BoardColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Card createCard(String title, String description, Integer position, Long columnId) {
        BoardColumn column = boardColumnRepository.findById(columnId)
                .orElseThrow(() -> new ResourceNotFoundException("column not found with id : " + columnId));

        Card card = new Card();
        card.setTitle(title);
        card.setDescription(description);
        card.setPosition(position);
        card.setColumn(column);

        return cardRepository.save(card);
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card moveCard(Long cardId, Long newColumnId, int newPosition) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + cardId));

        BoardColumn newColumn = boardColumnRepository.findById(newColumnId)
                .orElseThrow(() -> new ResourceNotFoundException("Column not found with id: " + newColumnId));

        card.setColumn(newColumn);
        card.setPosition(newPosition);

        Card savedCard = cardRepository.save(card);

        messagingTemplate.convertAndSend(
                "/topic/board/" + newColumn.getBoard().getId(),
                new CardResponseDTO(savedCard.getId(), savedCard.getTitle(), savedCard.getDescription(),
                        savedCard.getPosition(), savedCard.getColumn().getId())
        );

        return savedCard;
    }
}