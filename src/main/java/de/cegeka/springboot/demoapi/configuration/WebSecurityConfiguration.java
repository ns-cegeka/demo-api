/**
 * Configuration for securing the API.
 *
 * created on: 19.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/company/**").hasRole("INFO")
      .antMatchers("/temp/**").hasRole("ADMIN")
      .and()
      .httpBasic()
      .and().csrf().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("user").password("{noop}geheim").roles("INFO")
      .and()
      .withUser("admin").password("{noop}geheimer").roles("INFO", "ADMIN");
  }
}
