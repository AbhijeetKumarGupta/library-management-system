package com.sojoteki.library_management_system.controller;

import com.sojoteki.library_management_system.model.Transaction;
import com.sojoteki.library_management_system.request_dto.TransactionRequestDto;
import com.sojoteki.library_management_system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        try {
            String response = transactionService.saveTransaction(transactionRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass()+":\n"+"Save operation failed - "+e.getMessage());
        }
    }

    @GetMapping("")
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable int id){
        try {
            Transaction transaction = transactionService.getTransactionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(transaction);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getClass()+":\n"+"Get operation failed - "+e.getMessage());
        }
    }
}
