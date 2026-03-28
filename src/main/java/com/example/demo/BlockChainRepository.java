package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockChainRepository extends JpaRepository<Block, Long> {
    Block findTopByOrderByIdDesc();
}