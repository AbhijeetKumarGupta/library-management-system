package com.sojoteki.library_management_system.request_dto;

import com.sojoteki.library_management_system.enums.CardStatus;
import lombok.Data;

@Data
public class CardRequestDto {
    private CardStatus cardStatus;
    private String expiryDate;

    private int studentId;
}
