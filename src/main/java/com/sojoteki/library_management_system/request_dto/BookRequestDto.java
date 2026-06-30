package com.sojoteki.library_management_system.request_dto;

import lombok.Data;

@Data
public class BookRequestDto {
    private String title;
    private String publisherName;
    private String publishedDate;
    private int pages;
    private boolean availability;
    private String category;
    private int rackNo;

    private int cardId;
}
