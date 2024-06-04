package com.example.studentmanagament.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.example.studentmanagament.response.ErrorResponse;


@ControllerAdvice
public class GloabalExceptionHandler {
 public ResponseEntity<ErrorResponse> handleStudentObjectIsNull(StudentObjectISNull ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
 public ResponseEntity<ErrorResponse>handleStudentIdNotFound(StudentIdIsNotFound ex)
 {
    ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
 }
 public ResponseEntity<ErrorResponse>handleDataIsNotFound(DataIsNotPresent ex)
 {
    ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
 }
}