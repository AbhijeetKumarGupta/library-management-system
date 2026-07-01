package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.exception.ResourceNotFoundException;
import com.sojoteki.library_management_system.model.Card;
import com.sojoteki.library_management_system.repository.CardRepository;
import com.sojoteki.library_management_system.request_dto.CardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public String saveCard(CardRequestDto cardRequestDto) {
        Card card = new Card();
        applyRequest(card, cardRequestDto);
        cardRepository.save(card);

        return "Card saved successfully";
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(int cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card with id " + cardId + " not found"));
    }

    public String updateCard(int cardId, CardRequestDto cardRequestDto) {
        Card card = getCardById(cardId);
        applyRequest(card, cardRequestDto);
        cardRepository.save(card);
        return "Card updated successfully";
    }

    public String deleteCard(int cardId) {
        Card card = getCardById(cardId);
        cardRepository.delete(card);
        return "Card deleted successfully";
    }

    private void applyRequest(Card card, CardRequestDto cardRequestDto) {
        card.setCardStatus(cardRequestDto.getCardStatus());
        card.setExpiryDate(cardRequestDto.getExpiryDate());
    }
}
