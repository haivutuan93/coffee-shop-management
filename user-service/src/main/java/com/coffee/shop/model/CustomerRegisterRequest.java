package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * RegisterRequest
 */
@Validated
@Data
public class CustomerRegisterRequest {
  @JsonProperty("mobile_number")
  private String mobileNumber = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("address")
  private String address = null;
}
