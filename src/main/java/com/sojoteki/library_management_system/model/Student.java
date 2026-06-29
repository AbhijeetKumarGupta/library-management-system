package com.sojoteki.library_management_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sojoteki.library_management_system.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "semester", nullable = false)
    private String semester;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "dob", nullable = false)
    private String dob;

    @JsonManagedReference
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Card card;
}
