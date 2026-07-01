package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.enums.TransactionType;
import com.sojoteki.library_management_system.exception.BadRequestException;
import com.sojoteki.library_management_system.exception.ResourceNotFoundException;
import com.sojoteki.library_management_system.model.Book;
import com.sojoteki.library_management_system.model.Card;
import com.sojoteki.library_management_system.model.Transaction;
import com.sojoteki.library_management_system.repository.BookRepository;
import com.sojoteki.library_management_system.repository.CardRepository;
import com.sojoteki.library_management_system.repository.TransactionRepository;
import com.sojoteki.library_management_system.request_dto.TransactionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final BookRepository bookRepository;

    @Transactional
    public String saveTransaction(TransactionRequestDto transactionRequestDto) {
        Card card = cardRepository.findById(transactionRequestDto.getCardId())
                .orElseThrow(() -> new ResourceNotFoundException("Card with id " + transactionRequestDto.getCardId() + " not found"));
        Book book = bookRepository.findById(transactionRequestDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + transactionRequestDto.getBookId() + " not found"));

        Transaction transaction = new Transaction();
        transaction.setDueDate(transactionRequestDto.getDueDate());
        transaction.setTransactionType(transactionRequestDto.getTransactionType());
        transaction.setCard(card);
        transaction.setBook(book);

        applyBookState(transactionRequestDto.getTransactionType(), book, card);
        transactionRepository.save(transaction);

        return "Transaction saved successfully";
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(int transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id " + transactionId + " not found"));
    }

    private void applyBookState(TransactionType transactionType, Book book, Card card) {
        if (transactionType == TransactionType.BORROW) {
            if (!book.isAvailability()) {
                throw new BadRequestException("Book with id " + book.getId() + " is not available");
            }

            book.setAvailability(false);
            book.setCard(card);
            return;
        }

        if (transactionType == TransactionType.RETURN) {
            if (book.getCard() == null || book.getCard().getId() != card.getId()) {
                throw new BadRequestException("Book with id " + book.getId() + " is not borrowed on card " + card.getId());
            }

            book.setAvailability(true);
            book.setCard(null);
        }
    }
}
