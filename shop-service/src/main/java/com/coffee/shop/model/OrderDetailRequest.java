package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * OrderDetailRequest
 */
@Validated
@Data
public class OrderDetailRequest   {
  @JsonProperty("shop_product_id")
  private Long shopProductId = null;

  @JsonProperty(value = "quantity", defaultValue = "1")
  private Long quantity;

}
