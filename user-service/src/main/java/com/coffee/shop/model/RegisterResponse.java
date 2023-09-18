package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * RegisterResponse
 */
@Validated
@Data
@Builder
public class RegisterResponse   {
  @JsonProperty("user_id")
  private Integer userId = null;

  @JsonProperty("mobile_number")
  private String mobileNumber = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("address")
  private String address = null;

}
