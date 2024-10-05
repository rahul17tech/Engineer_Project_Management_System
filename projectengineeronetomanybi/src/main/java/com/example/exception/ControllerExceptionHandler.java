package com.example.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> customExceptionHandler(ResourceNotFoundException ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> customExcepHandler(MethodArgumentNotValidException ex) {
		Map<String, String> mp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();

			mp.put(fieldName, message);
		});
		return new ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest req) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
