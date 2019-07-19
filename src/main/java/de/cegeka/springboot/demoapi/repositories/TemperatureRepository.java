/**
 * Repository for storing the temperature value.
 *
 * created on: 19.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.cegeka.springboot.demoapi.model.TemperatureDAO;

@Repository
public interface TemperatureRepository extends CrudRepository<TemperatureDAO, Long>{
  public TemperatureDAO findFirst1ByOrderByDateTimeDesc();
  @Query("SELECT t FROM TemperatureDAO t WHERE t.temperature > :temp")
  public List<TemperatureDAO> getTemperaturesHigherThan(@Param("temp") float temperature);
}
