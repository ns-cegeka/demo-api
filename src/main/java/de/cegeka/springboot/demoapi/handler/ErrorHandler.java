/**
 * ErrorHandler for the exception handling.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.cegeka.springboot.demoapi.exceptions.TemperatureOutOfScopeException;
import de.cegeka.springboot.demoapi.model.ApiError;

@ControllerAdvice
public class ErrorHandler {
  
  @ExceptionHandler(TemperatureOutOfScopeException.class)
  public ResponseEntity<ApiError> handleIllegalArgumentException(TemperatureOutOfScopeException exception) {
    return new ResponseEntity<ApiError>(new ApiError(HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now(), exception.getMessage()), HttpStatus.NOT_ACCEPTABLE);
  }
}
