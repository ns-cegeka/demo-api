/**
 * Model for API errors.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 * 
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 **/

package de.cegeka.springboot.demoapi.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
   private HttpStatus status;
   private LocalDateTime timestamp;
   private String message;
}
