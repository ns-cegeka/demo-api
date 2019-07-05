/**
 * Controller for analyzing the temperature.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cegeka.springboot.demoapi.services.TemperatureService;
import de.cegeka.springboot.demoapi.services.TemperatureService.TemperatureLevel;

@RestController
@RequestMapping("/temp")
public class TemperatureController {
  private TemperatureService service;
  
  public TemperatureController(TemperatureService service) {
    this.service = service;
  }

  @GetMapping("/{value}")
  public TemperatureLevel analyze(@PathVariable("value") float value) {
    return service.analyze(value);
  }
}
