package com.prithwi.studentcrud.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.prithwi.studentcrud.dto.ErrorResponse;
import com.prithwi.studentcrud.exception.StudentCrudAppException;
import com.prithwi.studentcrud.exception.service.NoResultFounndException;

@RestControllerAdvice
public class StudentCrudControllerHandler {

	@ExceptionHandler(NoResultFounndException.class )
	public ResponseEntity<?> noStudent(StudentCrudAppException ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getCause(), ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

}
