package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controller.productController;

@ControllerAdvice
public class productExceptionController {
	Logger logger = LoggerFactory.getLogger(productController.class);
	
	@ExceptionHandler(value = ProductNotfoundException.class)
	   public ResponseEntity<Object> exception(ProductNotfoundException exception) {
		  logger.error("An exception occurred!", new Exception("Product Not Found :("));
	      return new ResponseEntity<>("Product not found!!!", HttpStatus.NOT_FOUND);
	   }
}
