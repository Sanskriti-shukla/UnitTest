package com.example.unitTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Document(collection = "userInfo")
    public class User {
        @Id

        String id;

        String firstName;

        String lastName;

        Date birthDate;

        String email;

        String city;

        String state;

        String country;

        @JsonIgnore
        boolean softDelete;


    }


