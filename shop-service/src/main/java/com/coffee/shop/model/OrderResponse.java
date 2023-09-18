package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * OrderResponse
 */
@Validated
@Data
@Builder
public class OrderResponse {
  @JsonProperty("order_id")
  private Long orderId = null;

  @JsonProperty("shop_id")
  private Long shopId = null;

  @JsonProperty("queue_id")
  private Long queueId = null;

  @JsonProperty("total_price")
  private BigDecimal totalPrice = null;

  @JsonProperty("customer")
  private CustomerInfo customer = null;

  @JsonProperty("position_in_queue")
  private Long positionInQueue = null;

  @JsonProperty("expected_wait_time")
  private Long expectedWaitTime = null;

  @JsonProperty("order_status")
  private String orderStatus = null;

  @JsonProperty("details")
  @Valid
  private List<OrderDetailResponse> details = null;

}
