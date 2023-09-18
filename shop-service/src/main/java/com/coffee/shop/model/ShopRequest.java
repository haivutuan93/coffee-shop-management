package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * ShopRequest
 */
@Validated
@Data
public class ShopRequest   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("latitude")
  private BigDecimal latitude = null;

  @JsonProperty("longitude")
  private BigDecimal longitude = null;

  @JsonProperty("contact_details")
  private String contactDetails = null;

  @JsonProperty("queue_quantity")
  private Long queueQuantity = null;

  @JsonProperty("queue_size")
  private Long queueSize = null;

  @JsonProperty("opening_time")
  private String openingTime = null;

  @JsonProperty("closing_time")
  private String closingTime = null;

}
