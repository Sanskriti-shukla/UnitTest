package com.example.unitTest.repository;


import com.example.unitTest.decorator.ImageData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StorageRepository extends MongoRepository<ImageData,String> {

    Optional<ImageData> findByName(String fileName);
}
