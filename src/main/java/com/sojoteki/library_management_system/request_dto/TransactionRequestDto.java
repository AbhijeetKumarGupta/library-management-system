package com.sojoteki.library_management_system.request_dto;

import com.sojoteki.library_management_system.enums.TransactionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionRequestDto {
    @NotBlank(message = "Due date is required")
    private String dueDate;

    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @Min(value = 1, message = "Card id is required")
    private int cardId;

    @Min(value = 1, message = "Book id is required")
    private int bookId;
}
