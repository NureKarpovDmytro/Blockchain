package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HybridStorageService {

    private final ImageRepository imageRepository;
    private final BlockChainRepository blockChainRepository;

    public HybridStorageService(ImageRepository imageRepository, BlockChainRepository blockChainRepository) {
        this.imageRepository = imageRepository;
        this.blockChainRepository = blockChainRepository;
    }

    public Block uploadImage(String fileName, String fakeImageData) {
        Image image = new Image();
        image.setFileName(fileName);
        image.setFilePath("/hybrid_storage/" + fileName);
        image = imageRepository.save(image);

        Block lastBlock = blockChainRepository.findTopByOrderByIdDesc();
        String previousHash = lastBlock != null ? lastBlock.getImageHash() : "00000000000000000000000000000000";

        String newHash = HashUtil.applySha256(fakeImageData + fileName + previousHash);

        Block newBlock = new Block();
        newBlock.setImageId(image.getId());
        newBlock.setImageHash(newHash);
        newBlock.setPrevHash(previousHash);

        return blockChainRepository.save(newBlock);
    }

    // НОВЫЙ МЕТОД: достает всю цепочку из базы
    public List<Block> getAllBlocks() {
        return blockChainRepository.findAll();
    }
}