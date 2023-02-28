package com.example.unitTest.common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class DataResponse<T> {
        T data;
        Response status;


    }


