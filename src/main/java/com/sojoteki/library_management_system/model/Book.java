package com.sojoteki.library_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @Column(name = "published_date", nullable = false)
    private String publishedDate;

    @Column(name = "pages", nullable = false)
    private int pages;

    @Column(name = "availability", nullable = false)
    private boolean availability;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "rack_no", nullable = false)
    private int rackNo;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Card card;

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    private List<Transaction> transactions;
}
