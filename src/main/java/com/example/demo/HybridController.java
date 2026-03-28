package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HybridController {

    private final HybridStorageService service;

    public HybridController(HybridStorageService service) {
        this.service = service;
    }

    @GetMapping("/upload")
    public String upload(@RequestParam String fileName, @RequestParam String data) {
        Block newBlock = service.uploadImage(fileName, data);
        return "Файл " + fileName + " успішно завантажено! Хеш: " + newBlock.getImageHash();
    }

    // НОВЫЙ МЕТОД: выводит блокчейн на экран
    @GetMapping("/blocks")
    public List<Block> getAllBlocks() {
        return service.getAllBlocks();
    }
}