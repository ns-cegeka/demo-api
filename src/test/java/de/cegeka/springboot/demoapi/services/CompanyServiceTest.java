/**
 * Test For the CompanyService.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.env.Environment;

import de.cegeka.springboot.demoapi.configuration.CompanyProperties;

public class CompanyServiceTest {
  
  private CompanyService service;
  private Environment environment = mock(Environment.class);

  @Before
  public void setup() {
    when(environment.getProperty("company.name")).thenReturn("JS AG");
    when(environment.getProperty("company.boss.fullname")).thenReturn("Igor Igel");

    CompanyProperties properties = new CompanyProperties();
    properties.setName("Java AG");
    properties.getBoss().setName("von der Wolga");
    properties.getBoss().setSurname("Olga");
    properties.getBoss().setFullname("Olga von der Wolga");
    
    service = new CompanyService(environment, properties);
  }
  
  @Test
  public void testCompanyName() {
    assertEquals("Props: Java AG", service.getName());
  }
  
  @Test
  public void testCompanyInfo() {
    assertTrue(service.getInfo().contains("Java AG"));
    assertTrue(service.getInfo().contains("Olga"));
  }
  
  @Test
  public void testCompanyNameFromEnvironment() {
    service = new CompanyService(environment, null);
    assertEquals("Env: JS AG", service.getName());
  }

  @Test
  public void testCompanyInfoFromEnvironment() {
    service = new CompanyService(environment, null);
    
    assertTrue(service.getInfo().contains("JS AG"));
    assertTrue(service.getInfo().contains("Igor"));
  }
}
