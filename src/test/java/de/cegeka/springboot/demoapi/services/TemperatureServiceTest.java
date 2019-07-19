/**
 * Test for the TemperatureService.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.mockito.Mockito;

import de.cegeka.springboot.demoapi.exceptions.TemperatureOutOfScopeException;
import de.cegeka.springboot.demoapi.model.TemperatureDAO;
import de.cegeka.springboot.demoapi.repositories.TemperatureRepository;
import de.cegeka.springboot.demoapi.services.TemperatureService.TemperatureLevel;

public class TemperatureServiceTest {
  private TemperatureRepository repository = Mockito.mock(TemperatureRepository.class);
  private TemperatureService service = new TemperatureService(repository);
  
  @Test(expected = TemperatureOutOfScopeException.class)
  public void testTemperatureThrowsException() {
    service.analyze(50);
  }
  
  @Test
  public void testTemperatureIsVeryCold() {
    assertEquals(TemperatureLevel.VERY_COLD, service.analyze(-10.8f));
  }
  
  @Test
  public void testTemperatureIsCold() {
    assertEquals(TemperatureLevel.COLD, service.analyze(3));
  }

  @Test
  public void testTemperatureIsWarm() {
    assertEquals(TemperatureLevel.WARM, service.analyze(20.9f));
  }

  @Test
  public void testTemperatureIsHot() {
    assertEquals(TemperatureLevel.HOT, service.analyze(31));
  }
  
  @Test
  public void testSave() {
    TemperatureDAO saveDAO = new TemperatureDAO(null, 10f, null);
    TemperatureDAO expectedDAO = new TemperatureDAO(1L, 10f, LocalDateTime.now());
    
    Mockito.when(repository.save(saveDAO)).thenReturn(expectedDAO);
    TemperatureDAO dao = service.saveTemperature(10f);
    
    assertEquals(expectedDAO.getId(), dao.getId());
    assertEquals(Float.valueOf(expectedDAO.getTemperature()), Float.valueOf(dao.getTemperature()));
  }

}
