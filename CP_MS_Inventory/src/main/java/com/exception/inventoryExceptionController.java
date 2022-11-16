package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controller.inventoryController;


@ControllerAdvice
public class inventoryExceptionController {
Logger logger = LoggerFactory.getLogger(inventoryController.class);
	
	@ExceptionHandler(value = InventoryNotFoundException.class)
	   public ResponseEntity<Object> exception(InventoryNotFoundException exception) {
		  logger.error("An exception occurred!", new Exception("Inventory Not Found :("));
	      return new ResponseEntity<>("Inventory not found!!!", HttpStatus.NOT_FOUND);
	   }
}
