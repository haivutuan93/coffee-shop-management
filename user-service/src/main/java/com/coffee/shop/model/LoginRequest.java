package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * LoginRequest
 */
@Validated
@Data
public class LoginRequest   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

}
