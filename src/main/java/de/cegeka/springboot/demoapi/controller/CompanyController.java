/**
 * Controller for getting information about the company.
 *
 * created on: 28.06.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.cegeka.springboot.demoapi.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
  private CompanyService service;
  
  public CompanyController(CompanyService service) {
    this.service = service;
  }
  
  @GetMapping("/info")
  public String getCompanyInfo() {
    return service.getInfo();
  }
  
  @GetMapping("/name")
  public String getCompanyName() {
    return service.getName();
  }
}
