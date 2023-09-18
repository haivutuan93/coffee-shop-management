package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * DeviceRegisterRequest
 */
@Validated
@Data
public class DeviceRegisterRequest   {
  @JsonProperty("device_token")
  private String deviceToken = null;
}
