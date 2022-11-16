package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controller.cartController;


@ControllerAdvice
public class cartExceptionController {
Logger logger = LoggerFactory.getLogger(cartController.class);
	
	@ExceptionHandler(value = cartNotFoundException.class)
	   public ResponseEntity<Object> exception(cartNotFoundException exception) {
		  logger.error("An exception occurred!", new Exception("Cart Not Found :("));
	      return new ResponseEntity<>("cart not found!!!", HttpStatus.NOT_FOUND);
	   }
}
