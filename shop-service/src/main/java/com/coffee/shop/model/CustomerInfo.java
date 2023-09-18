package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * CustomerInfo
 */
@Validated
@Data
@AllArgsConstructor
@Builder
public class CustomerInfo   {
  @JsonProperty("customer_id")
  private Long customerId = null;

  @JsonProperty("customer_name")
  private String customerName = null;

}
