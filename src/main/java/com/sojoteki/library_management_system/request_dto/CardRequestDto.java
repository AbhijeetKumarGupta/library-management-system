package com.sojoteki.library_management_system.request_dto;

import com.sojoteki.library_management_system.enums.CardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardRequestDto {
    @NotNull(message = "Card status is required")
    private CardStatus cardStatus;

    @NotBlank(message = "Expiry date is required")
    private String expiryDate;
}
