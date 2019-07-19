/**
 * Test For the TemperatureController.
 *
 * created on: 19.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.cegeka.springboot.demoapi.model.ApiError;
import de.cegeka.springboot.demoapi.repositories.TemperatureRepository;
import de.cegeka.springboot.demoapi.services.CompanyService;
import de.cegeka.springboot.demoapi.services.TemperatureService;
import de.cegeka.springboot.demoapi.services.TemperatureService.TemperatureLevel;

@RunWith(SpringRunner.class)
@WebMvcTest(TemperatureController.class)
@ComponentScan(basePackageClasses = TemperatureService.class)
public class TemperatureControllerTest {
  @MockBean
  private CompanyService companyService;

  @MockBean
  private TemperatureRepository repository;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;
  
  @Test
  public void testAnalyzeWithResultCold() throws UnsupportedEncodingException, Exception {
    String result = mvc.perform(get("/temp/10")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    
    TemperatureLevel level = mapper.readValue(result, TemperatureLevel.class);
    assertEquals(TemperatureLevel.COLD, level);
  }

  @Test
  public void testAnalyzeThrowsException() throws UnsupportedEncodingException, Exception {
    String result = mvc.perform(get("/temp/99")).andExpect(status().isNotAcceptable()).andReturn().getResponse().getContentAsString();
    
    ApiError error = mapper.readValue(result, ApiError.class);
    assertEquals("The temperature is out of scope: 99.0", error.getMessage());
  }


}
