package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * QueueInfo
 */
@Validated
@Data
public class QueueInfo   {
  @JsonProperty("queue_size")
  private Long queueSize = null;

  @JsonProperty("waiting_customers")
  private Long waitingCustomers = null;

}
