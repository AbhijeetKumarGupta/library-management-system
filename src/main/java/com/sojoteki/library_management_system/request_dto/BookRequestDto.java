package com.sojoteki.library_management_system.request_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Publisher name is required")
    private String publisherName;

    @NotBlank(message = "Published date is required")
    private String publishedDate;

    @Min(value = 1, message = "Pages must be at least 1")
    private int pages;

    private boolean availability;

    @NotBlank(message = "Category is required")
    private String category;

    @Min(value = 1, message = "Rack number must be at least 1")
    private int rackNo;
}
