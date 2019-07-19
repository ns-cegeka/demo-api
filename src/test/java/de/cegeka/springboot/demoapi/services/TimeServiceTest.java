/**
 * Test for the TimeService.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import de.cegeka.springboot.demoapi.model.TimeRequest;
import de.cegeka.springboot.demoapi.model.TimeResponse;

public class TimeServiceTest {
  private TimeService service = new TimeService();
  
  @Test
  public void testAllTimeZones() {
    assertTrue(service.getAllTimezones().size() > 0);
  }
  
  @Test
  public void testCurrentDateTime() {
    String timestamp = service.getCurrentDateTime(ZoneId.systemDefault());
    String timeToCompare = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    
    assertEquals(timeToCompare.subSequence(0, timeToCompare.lastIndexOf(".")), timestamp.subSequence(0, timestamp.lastIndexOf(".")));
  }

  @Test
  public void testCurrentDateTimeWithModel() {
    TimeRequest request = new TimeRequest();
    request.setZoneId(ZoneId.systemDefault());
    
    TimeResponse response = service.getCurrentDateTime2(request);
    LocalDateTime timeToCompare = LocalDateTime.now();
    
    assertEquals(timeToCompare.getHour(), response.getCurrentTime().getHour());
    assertEquals(timeToCompare.getMinute(), response.getCurrentTime().getMinute());
    assertEquals(timeToCompare.getSecond(), response.getCurrentTime().getSecond());
  }
}
