/**
 * Service for the CompanyController.
 *
 * created on: 05.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import de.cegeka.springboot.demoapi.configuration.CompanyProperties;

@Service
public class CompanyService {
  private CompanyProperties properties;
  private Environment env;

  public CompanyService(Environment env, @Autowired(required = false) CompanyProperties properties) {
    this.properties = properties;
    this.env = env;
  }
  
  public String getInfo() {
    if (properties != null) {
      return "Props: " + properties.getName() + ": " + properties.getBoss().getFullname();
    }
    return "Env: " + env.getProperty("company.name") + ":" + env.getProperty("company.boss.fullname");
  }
  
  public String getName() {
    if (properties != null) {
      return "Props: " + properties.getName();
    }
    return "Env: " + env.getProperty("company.name");
  }
}
