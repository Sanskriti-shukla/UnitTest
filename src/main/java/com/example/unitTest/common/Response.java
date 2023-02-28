package com.example.unitTest.common;
import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

    @Data
    @NoArgsConstructor
    @ToString
    public class Response {
        HttpStatus code;
        String status;
        String description;
        public Response(HttpStatus ok, String ok1, String okDescription) {
            this.code = ok;
            this.status = ok1;
            this.description = okDescription;
        }

        public static Response getOkResponse(String message) {
            return new Response(HttpStatus.OK, ResponseConstant.OK,message);

        }




}
