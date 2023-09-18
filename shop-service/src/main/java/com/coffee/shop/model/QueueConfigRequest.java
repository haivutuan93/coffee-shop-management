package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * QueueConfigRequest
 */
@Validated
@Data
public class QueueConfigRequest   {
  @JsonProperty("quantity")
  private Long quantity = null;

  @JsonProperty("queue_size")
  private Long queueSize = null;

}
