package com.example.unitTest.service;


import com.example.unitTest.model.User;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.math3.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {


        Map<String, List<User>> getUsersByCity();

        Map<Pair<String, String>, List<User>> getUsersByStateAndCity();

        Map<Triple<String, String, String>, List<User>> getUsersByCountryStateAndCity();

        Set<String> getAllCity();

        List<User> sortByBirthAndFirstName();

        Map<String, User> getUserByEmail();

        List<User> getUserByAge();

        String uploadImage( MultipartFile file) throws IOException;

        byte[] downloadImage(String fileName);

        List<User> addEmail();
}



