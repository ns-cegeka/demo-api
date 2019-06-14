/**
 * Request model for the TimeController.
 *
 * created on: 14.06.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.model;

import java.time.ZoneId;

import lombok.Data;

@Data
public class TimeRequest {
  private ZoneId zoneId;
}
