/**
 * Controller for the world's time.
 *
 * created on: 14.06.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cegeka.springboot.demoapi.model.TimeRequest;
import de.cegeka.springboot.demoapi.model.TimeResponse;

@RestController
@RequestMapping("/time")
public class TimeController {

  @GetMapping("/timezones")
  public Set<String> getAllTimezones() {
    return ZoneId.getAvailableZoneIds();
  }

  @GetMapping("/current/{zone}")
  public String getCurrentDateTime(@PathVariable("zone") ZoneId zoneId) {
    return LocalDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
  }

  @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public TimeResponse getCurrentDateTime2(@RequestBody TimeRequest model) {
    return new TimeResponse(LocalDateTime.now(model.getZoneId()));
  }

}
