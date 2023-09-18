package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderRequest
 */
@Validated
@Data
public class OrderRequest   {
  @JsonProperty("order_details")
  @Valid
  private List<OrderDetailRequest> orderDetails = new ArrayList<OrderDetailRequest>();
}
