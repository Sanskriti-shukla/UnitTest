package com.example.unitTest.repository;

import com.example.unitTest.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllBySoftDeleteFalse();
}
