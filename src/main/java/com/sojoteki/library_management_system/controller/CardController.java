package com.sojoteki.library_management_system.controller;

import com.sojoteki.library_management_system.model.Card;
import com.sojoteki.library_management_system.request_dto.CardRequestDto;
import com.sojoteki.library_management_system.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCard(@Valid @RequestBody CardRequestDto cardRequestDto) {
        String response = cardService.saveCard(cardRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable int id) {
        return cardService.getCardById(id);
    }

    @PutMapping("/update/{id}")
    public String updateCard(@PathVariable int id, @Valid @RequestBody CardRequestDto cardRequestDto) {
        return cardService.updateCard(id, cardRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCard(@PathVariable int id) {
        return cardService.deleteCard(id);
    }
}
