package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * ShopProduct
 */
@Validated
@Data
public class ShopProduct   {
  @JsonProperty("shop_product_id")
  private Long shopProductId = null;

  @JsonProperty("price")
  private BigDecimal price = null;

  @JsonProperty("quantity_remaining")
  private Long quantityRemaining = null;
}
