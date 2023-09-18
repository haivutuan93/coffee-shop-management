package com.coffee.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * ErrorModel
 */
@Validated
@Data
@Builder
public class ErrorModel {
  @JsonProperty("error_code")
  private Integer errorCode = null;

  @JsonProperty("error_message")
  private String errorMessage = null;

}
