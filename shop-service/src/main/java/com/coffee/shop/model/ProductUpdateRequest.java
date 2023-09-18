package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * ProductUpdateRequest
 */
@Validated
@Data
public class ProductUpdateRequest   {
  @JsonProperty("product_id")
  private Long productId = null;

  @JsonProperty("price")
  private BigDecimal price = null;

}
