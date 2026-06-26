package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.model.Card;
import com.sojoteki.library_management_system.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public String saveCard(Card card){
        cardRepository.save(card);
        return "Card saved successfully";
    }

    public List<Card> getAllCards(){
        return cardRepository.findAll();
    }

    public Card getCardById(int cardId){
        Optional<Card> card = cardRepository.findById(cardId);

        return card.orElse(null);
    }
}
