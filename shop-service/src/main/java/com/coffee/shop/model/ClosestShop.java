package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * ClosestShop
 */
@Validated
@Data
public class ClosestShop  {
  @JsonProperty("shop_id")
  private Long shopId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("distance")
  private BigDecimal distance = null;

  @JsonProperty("latitude")
  private BigDecimal latitude = null;

  @JsonProperty("longitude")
  private BigDecimal longitude = null;

}
