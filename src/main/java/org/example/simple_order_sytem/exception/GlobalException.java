package org.example.simple_order_sytem.exception;

import org.example.simple_order_sytem.dto.ErrorDto;
import org.example.simple_order_sytem.dto.OfficeDto;
import org.example.simple_order_sytem.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exception(MethodArgumentNotValidException e) {
        List<ErrorDto> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String code = error.getCode();
            String message = error.getDefaultMessage();
            errors.add(new ErrorDto(code, message));
        });
        return new ResponseEntity<>(Response.<String>builder()
                .errors(errors)
                .build(), HttpStatus.OK);
    }
}
