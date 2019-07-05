/**
 * Exception thrown on invalid temperature.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.exceptions;

public class TemperatureOutOfScopeException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TemperatureOutOfScopeException(String message) {
    super(message);
  }
}
