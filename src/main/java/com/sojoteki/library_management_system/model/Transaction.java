package com.sojoteki.library_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sojoteki.library_management_system.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "transaction_date", nullable = false)
    @CreationTimestamp
    private Date transactionDate;

    @Column(name = "due_date", nullable = false)
    private String dueDate;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Card card;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Book book;
}
