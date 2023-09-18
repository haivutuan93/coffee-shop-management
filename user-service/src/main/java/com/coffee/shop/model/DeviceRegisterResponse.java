package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * DeviceRegisterResponse
 */
@Validated
@Data
public class DeviceRegisterResponse   {
  @JsonProperty("device_id")
  private String deviceId = null;

  @JsonProperty("device_token")
  private String deviceToken = null;

}
