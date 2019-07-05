/**
 * Controller for getting information about the company.
 *
 * created on: 28.06.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cegeka.springboot.demoapi.configuration.CompanyProperties;

@RestController
@RequestMapping("/company")
public class CompanyController {
  @Autowired(required = false)
  private CompanyProperties properties;
  
  private Environment env;

  public CompanyController(Environment env) {
    this.env = env;
  }
  
  @GetMapping("/info")
  public String getCompanyInfo() {
    if (properties != null) {
      return "Props: " + properties.getName() + ": " + properties.getBoss().getFullname();
    }
    return "Env: " + env.getProperty("company.name") + ":" + env.getProperty("company.boss.fullname");
  }
  
  @GetMapping("/name")
  public String getCompanyName() {
    if (properties != null) {
      return "Props: " + properties.getName();
    }
    return "Env: " + env.getProperty("company.name");
  }
}
