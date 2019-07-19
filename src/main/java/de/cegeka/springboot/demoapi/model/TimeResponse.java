/**
 * Response model for the TimeController.
 *
 * created on: 14.06.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeResponse {
// @JsonDeserialize(using = LocalDateTimeDeserializer.class) for sending a date as string with the following pattern: yyyy-MM-ddTHH:mm:ss.SSS
  @JsonSerialize(using = LocalDateTimeSerializer.class) // or @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
  private LocalDateTime currentTime;
}
