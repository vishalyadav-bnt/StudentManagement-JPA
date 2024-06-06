package com.example.studentmanagament.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.studentmanagament.response.ErrorResponse;
import com.example.studentmanagament.service.StudentServiceimpl;

@ControllerAdvice
public class GloabalExceptionHandler {
   private static final Logger logger = LoggerFactory.getLogger(StudentServiceimpl.class);

   @ExceptionHandler(StudentObjectISNull.class)
   public ResponseEntity<ErrorResponse> handleStudentObjectIsNull(StudentObjectISNull ex) {
      logger.error("Student Object Is Empty:{}", ex.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
      return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
   }

   @ExceptionHandler(StudentIdIsNotFound.class)
   public ResponseEntity<ErrorResponse> handleStudentIdNotFound(StudentIdIsNotFound ex) {
      logger.error("Student Id Not Found {}", ex.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(DataIsNotPresent.class)
   public ResponseEntity<ErrorResponse> handleDataIsNotFound(DataIsNotPresent ex) {
      logger.error("Data Not Present", ex.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }
}