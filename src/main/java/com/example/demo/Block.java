package com.example.demo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "block_chain")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long imageId;
    private String imageHash;
    private String prevHash;
    private LocalDateTime createdAt = LocalDateTime.now();
}