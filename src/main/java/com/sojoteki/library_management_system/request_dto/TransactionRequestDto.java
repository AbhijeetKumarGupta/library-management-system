package com.sojoteki.library_management_system.request_dto;

import com.sojoteki.library_management_system.enums.TransactionType;
import lombok.Data;

@Data
public class TransactionRequestDto {
    private String dueDate;
    private TransactionType transactionType;

    private int cardId;
    private int bookId;
}
