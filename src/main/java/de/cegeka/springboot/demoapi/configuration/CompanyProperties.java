/**
 * Class for the company properties.
 *
 * created on: 28.06.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@Configuration
@PropertySource("classpath:/company.properties")
@ConfigurationProperties(prefix = "company")
@ConditionalOnProperty(value = "company.info.active", havingValue = "true")
public class CompanyProperties {
  private String name;
  private Boss boss = new Boss();
  
  @Data
  public class Boss {
    private String name;
    private String surname;
    private String fullname;
  }
}
