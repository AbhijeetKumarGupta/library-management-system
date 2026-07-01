package com.sojoteki.library_management_system.controller;

import com.sojoteki.library_management_system.model.Transaction;
import com.sojoteki.library_management_system.request_dto.TransactionRequestDto;
import com.sojoteki.library_management_system.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<String> saveTransaction(@Valid @RequestBody TransactionRequestDto transactionRequestDto) {
        String response = transactionService.saveTransaction(transactionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable int id) {
        return transactionService.getTransactionById(id);
    }
}
