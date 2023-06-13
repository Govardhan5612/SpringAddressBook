package com.bridgelabz.addressBook.exception;

import com.bridgelabz.addressBook.dto.ResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class PersonExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> methodArgumentHandler(MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream().map(objError ->objError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDto dto = new ResponseDto("Check the Person details",errorMessage);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PersonException.class)
    public ResponseEntity<ResponseDto> idHandler(PersonException exception){
        ResponseDto dto = new ResponseDto("Searching the user Id",exception.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDto> DataIntegrityViolationException(DataIntegrityViolationException exception){
        //String error = exception.getMessage();
        ResponseDto dto = new ResponseDto("This email is already present in data base",exception);
        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
    }

}
