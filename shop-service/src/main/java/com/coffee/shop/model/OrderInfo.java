package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * OrderInfo
 */
@Validated
@Data
public class OrderInfo   {
  @JsonProperty("order_id")
  private Long orderId = null;

  @JsonProperty("total_price")
  private BigDecimal totalPrice = null;

  @JsonProperty("position_in_queue")
  private Long positionInQueue = null;

  @JsonProperty("expected_wait_time")
  private String expectedWaitTime = null;

  @JsonProperty("order_status")
  private String orderStatus = null;

  @JsonProperty("customer")
  private CustomerInfo customer = null;
}
