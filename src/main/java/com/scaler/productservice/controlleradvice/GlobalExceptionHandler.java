package com.scaler.productservice.controlleradvice;

import com.scaler.productservice.DTO.ExceptionDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("message");
        exceptionDto.setSolution("solution");

        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException() {
        ResponseEntity<String> response = new ResponseEntity<>(
                "ArrayIndexOutOfBoundsException has happened, Inside the ControllerAdvice.",
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ExceptionDto exceptionDto) {
//        ExceptionDto exceptionDto = new ExceptionDto();
//        //TODO: Pss the input id in the ExceptionDto.
//        exceptionDto.setMessage("Product not found");
//        exceptionDto.setSolution("Please try again with a valid product id");

        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST
        );
        return response;
    }
}
