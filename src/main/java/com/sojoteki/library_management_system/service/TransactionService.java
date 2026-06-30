package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.model.Book;
import com.sojoteki.library_management_system.model.Card;
import com.sojoteki.library_management_system.model.Transaction;
import com.sojoteki.library_management_system.repository.BookRepository;
import com.sojoteki.library_management_system.repository.CardRepository;
import com.sojoteki.library_management_system.repository.TransactionRepository;
import com.sojoteki.library_management_system.request_dto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    public String saveTransaction(TransactionRequestDto transactionRequestDto){
        Card card = cardRepository.findById(transactionRequestDto.getCardId()).orElse(null);
        Book book = bookRepository.findById(transactionRequestDto.getBookId()).orElse(null);

        Transaction transaction = new Transaction();
        transaction.setDueDate(transactionRequestDto.getDueDate());
        transaction.setTransactionType(transactionRequestDto.getTransactionType());
        transaction.setCard(card);
        transaction.setBook(book);

        transactionRepository.save(transaction);

        if(book != null){
            book.setCard(card);
            bookRepository.save(book);
        }

        return "Transaction saved successfully";
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(int transactionId){
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);

        if(transaction.isPresent()){
            return transaction.get();
        }else{
            throw new RuntimeException("Transaction not found");
        }
    }
}
