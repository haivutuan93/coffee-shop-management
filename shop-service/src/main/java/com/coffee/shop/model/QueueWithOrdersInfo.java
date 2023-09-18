package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * QueueWithOrdersInfo
 */
@Validated
@Data
public class QueueWithOrdersInfo   {
  @JsonProperty("queue_id")
  private Long queueId = null;

  @JsonProperty("queue_size")
  private Long queueSize = null;

  @JsonProperty("waiting_customers")
  private Long waitingCustomers = null;

  @JsonProperty("orders")
  @Valid
  private List<OrderInfo> orders = null;

}
