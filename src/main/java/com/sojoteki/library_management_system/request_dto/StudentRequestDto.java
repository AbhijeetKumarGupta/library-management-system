package com.sojoteki.library_management_system.request_dto;

import com.sojoteki.library_management_system.enums.Gender;
import lombok.Data;

@Data
public class StudentRequestDto {
    private String name;
    private String email;
    private String mobile;
    private String department;
    private String semester;
    private Gender gender;
    private String address;
    private String dob;

    private int cardId;
}
