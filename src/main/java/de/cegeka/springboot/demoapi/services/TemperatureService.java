/**
 * Service for analyzing the temperature.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import de.cegeka.springboot.demoapi.exceptions.TemperatureOutOfScopeException;
import de.cegeka.springboot.demoapi.model.TemperatureDAO;
import de.cegeka.springboot.demoapi.repositories.TemperatureRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TemperatureService {
  public enum TemperatureLevel {
    VERY_COLD, COLD, WARM, HOT;
  }
  
  private TemperatureRepository repository;
  
  public TemperatureService(TemperatureRepository repository) {
    this.repository = repository;
  }
  
  public TemperatureLevel analyze(float temperature) {
    if (temperature < -30 || temperature > 40) {
      log.error("The temperature is out of scope: {}", temperature);
      throw new TemperatureOutOfScopeException(String.format("The temperature is out of scope: %s", temperature));
    } else {
      TemperatureLevel level = null;
      
      if (temperature > -30 && temperature <= 0) {
        level = TemperatureLevel.VERY_COLD;
        
      } else if (temperature > 0 && temperature <= 12) {
        level = TemperatureLevel.COLD;
      
      } else if (temperature > 12 && temperature <= 25) {
        level = TemperatureLevel.WARM;
      
      } else {
        level = TemperatureLevel.HOT;
      }
      
      if (log.isDebugEnabled()) log.debug("temperature is '{}' for {}", level.name(), temperature);
      return level;
    }
  }
  
  public TemperatureDAO saveTemperature(float temperature) {
    TemperatureDAO dao = new TemperatureDAO();
    dao.setTemperature(temperature);
    
    return repository.save(dao);
  }
  
  public TemperatureDAO getLatest() {
    return repository.findFirst1ByOrderByDateTimeDesc();
  }

  public List<TemperatureDAO> getTemperaturesHigherThan(float temperature) {
    return repository.getTemperaturesHigherThan(temperature);
  }

}
