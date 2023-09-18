package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * OrderDetail
 */
@Validated
@Data
@Builder
public class OrderDetailResponse {
  @JsonProperty("shop_product_id")
  private Long shopProductId = null;

  @JsonProperty("quantity")
  private Long quantity = null;

}
