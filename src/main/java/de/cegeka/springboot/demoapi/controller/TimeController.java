/**
 * Controller for the world's time.
 *
 * created on: 14.06.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.controller;

import java.time.ZoneId;
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
import de.cegeka.springboot.demoapi.services.TimeService;

@RestController
@RequestMapping("/time")
public class TimeController {
  private TimeService service;
  
  public TimeController(TimeService service) {
    this.service = service;
  }
  
  @GetMapping("/timezones")
  public Set<String> getAllTimezones() {
    return service.getAllTimezones();
  }

  @GetMapping("/current/{zone}")
  public String getCurrentDateTime(@PathVariable("zone") ZoneId zoneId) {
    return service.getCurrentDateTime(zoneId);
  }

  @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public TimeResponse getCurrentDateTime2(@RequestBody TimeRequest model) {
    return service.getCurrentDateTime2(model);
  }

}
