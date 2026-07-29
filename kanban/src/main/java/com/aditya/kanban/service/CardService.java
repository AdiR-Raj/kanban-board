package com.aditya.kanban.service;

import com.aditya.kanban.model.Card;
import com.aditya.kanban.model.BoardColumn;
import com.aditya.kanban.repository.CardRepository;
import com.aditya.kanban.repository.BoardColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BoardColumnRepository boardColumnRepository;

    public Card createCard(String title, String description, Integer position, Long columnId) {
        BoardColumn column = boardColumnRepository.findById(columnId)
                .orElseThrow(() -> new RuntimeException("column not found with id : " + columnId));

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
}
