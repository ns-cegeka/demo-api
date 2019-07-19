/**
 * Test For the TimeController.
 *
 * created on: 19.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.cegeka.springboot.demoapi.model.TimeRequest;
import de.cegeka.springboot.demoapi.model.TimeResponse;
import de.cegeka.springboot.demoapi.services.CompanyService;
import de.cegeka.springboot.demoapi.services.TimeService;

@RunWith(SpringRunner.class)
@WebMvcTest(TimeController.class)
public class TimeControllerTest {
  @MockBean
  private CompanyService companyService;

  @MockBean
  private TimeService service;

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper mapper;

  @Test
  public void testGetAllTimeZones() throws UnsupportedEncodingException, Exception {
    Mockito.when(service.getAllTimezones()).thenReturn(new HashSet<String>(Sets.newSet("Europe")));
    MvcResult result = mvc.perform(get("/time/timezones")).andExpect(status().isOk()).andReturn();
    String resultAsString = result.getResponse().getContentAsString();
    assertEquals(true, resultAsString.contains("Europe"));
  }

  @Test
  public void testGetCurrentDateTime() throws Exception {
    Mockito.when(service.getCurrentDateTime(ZoneId.of("Cuba"))).thenReturn("01.01.1970");
    String result = mvc.perform(get("/time/current/Cuba")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    assertEquals("01.01.1970", result);
  }

  @Test
  public void testGetCurrentDateTime2() throws UnsupportedEncodingException, Exception {
    TimeRequest model = new TimeRequest(ZoneId.systemDefault());
    LocalDateTime currentTime = LocalDateTime.of(1970, 1, 1, 12, 0);
    TimeResponse expectedResponse = new TimeResponse(currentTime);

    Mockito.when(service.getCurrentDateTime2(model)).thenReturn(expectedResponse);
    String result = mvc.perform(post("/time/current").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(mapper.writeValueAsString(model)))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    TimeResponse response = mapper.readValue(result, TimeResponse.class);
    assertEquals(expectedResponse, response);
  }

}
