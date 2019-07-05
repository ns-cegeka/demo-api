/**
 * Service for the TimeController.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import de.cegeka.springboot.demoapi.model.TimeRequest;
import de.cegeka.springboot.demoapi.model.TimeResponse;

@Service
public class TimeService {

  public Set<String> getAllTimezones() {
    return ZoneId.getAvailableZoneIds();
  }

  public String getCurrentDateTime(@PathVariable("zone") ZoneId zoneId) {
    return LocalDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
  }

  public TimeResponse getCurrentDateTime2(@RequestBody TimeRequest model) {
    return new TimeResponse(LocalDateTime.now(model.getZoneId()));
  }
}
