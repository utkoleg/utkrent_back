package com.example.sesia2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "qwe Not Found")
@Data
@AllArgsConstructor
public class EmailExistsException extends Exception{
    private String email;
}