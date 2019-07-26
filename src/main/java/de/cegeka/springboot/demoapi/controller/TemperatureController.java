/**
 * Controller for analyzing the temperature.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cegeka.springboot.demoapi.model.TemperatureDAO;
import de.cegeka.springboot.demoapi.services.TemperatureService;
import de.cegeka.springboot.demoapi.services.TemperatureService.TemperatureLevel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/temp")
public class TemperatureController {
  private TemperatureService service;

  public TemperatureController(TemperatureService service) {
    this.service = service;
  }

  @GetMapping("/{value}")
  @ApiOperation(value = "Analyzing the level of the given temperature")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Analyzing was OK"), @ApiResponse(code = 406, message = "The temperature is out of scope"), })
  public TemperatureLevel analyze(@ApiParam(value = "Temperature as float value", required = true) @PathVariable("value") float value) {
    return service.analyze(value);
  }
  
  @PostMapping("/{value}")
  @ApiOperation(value = "Saves the given temperature in the database")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Save successful") })
  public TemperatureDAO save(@ApiParam(value = "Temperature as float value", required = true) @PathVariable("value") float value) {
    return service.saveTemperature(value);
  }

  @GetMapping("/latest")
  @ApiOperation(value = "Gives the latest temperature from the database")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Get successful") })
  public TemperatureDAO getLatest() {
    return service.getLatest();
  }

  @GetMapping("/higher/{value}")
  @ApiOperation(value = "Gives a list with temperature higher than the given temperatures")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Get successful") })
  public List<TemperatureDAO> getTemperaturesHigherThan(@ApiParam(value = "Temperature as float value", required = true) @PathVariable("value") float value) {
    return service.getTemperaturesHigherThan(value);
  }

}
