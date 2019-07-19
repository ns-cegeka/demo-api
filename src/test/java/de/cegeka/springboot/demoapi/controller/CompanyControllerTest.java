/**
 * Test for the CompanyController.
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.cegeka.springboot.demoapi.services.CompanyService;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {
  @Autowired
  private MockMvc mvc;
  
  @MockBean
  private CompanyService service;
  
  @Test
  public void testGetCompanyInfo() throws UnsupportedEncodingException, Exception {
    Mockito.when(service.getInfo()).thenReturn("company info");
    String result = mvc.perform(get("/company/info")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    assertEquals("company info", result);
  }
  
  @Test
  public void testGetCompanyName() throws UnsupportedEncodingException, Exception {
    Mockito.when(service.getName()).thenReturn("company name");
    String result = mvc.perform(get("/company/name")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    assertEquals("company name", result);
  }

}
