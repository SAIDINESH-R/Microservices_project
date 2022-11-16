package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controller.CustomerController;


@ControllerAdvice
public class CustomerExceptionController {

		Logger logger = LoggerFactory.getLogger(CustomerController.class);
		
		@ExceptionHandler(value = CustomerNotfoundException.class)
		   public ResponseEntity<Object> exception(CustomerNotfoundException exception) {
			  logger.error("An exception occurred!", new Exception("Product Not Found :("));
		      return new ResponseEntity<>("Customer not found!!!", HttpStatus.NOT_FOUND);
		   }
		
}


