package com.coffee.shop.controller;

import com.coffee.shop.model.OrderRequest;
import com.coffee.shop.model.OrderResponse;
import com.coffee.shop.model.QueueWithOrdersInfo;
import com.coffee.shop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderManagementController {
    private final OrderService orderService;

    @Operation(summary = "Get the details of a specific order, including position in the queue and expected waiting time", description = "", tags = {"Order Management"})
    @RequestMapping(value = "/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<OrderResponse> shopsOrdersOrderIdGet(@PathVariable("orderId") Long orderId) {
        var response = orderService.getOrderById(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create an order from customer", description = "", tags = {"Order Management"})
    @RequestMapping(value = "/{shopId}/customer",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    ResponseEntity<OrderResponse> customerCreateAnOrder(@PathVariable("shopId") Long shopId, @Valid @RequestBody OrderRequest orderRequest) {
        var response = orderService.createOrder(shopId, orderRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Customer exits an order from a queue and cancels the order", description = "", tags = {"Order Management"})
    @RequestMapping(value = "/{shopId}/{orderId}/exit",
            method = RequestMethod.PUT)
    ResponseEntity<Void> shopsShopIdOrdersOrderIdExitPut(@PathVariable("shopId") Long shopId, @PathVariable("orderId") Long orderId) {
        return null;
    }

    @Operation(summary = "Remove an order from a queue and service them", description = "", tags = {"Order Management"})
    @RequestMapping(value = "/{shopId}/{orderId}/serve",
            method = RequestMethod.POST)
    ResponseEntity<Void> shopsShopIdOrdersOrderIdServePost(@PathVariable("shopId") Long shopId, @PathVariable("orderId") Long orderId) {
        return null;
    }

    @Operation(summary = "Get the details of all queues along with the orders in each queue for a shop", description = "", tags = {"Order Management"})
    @RequestMapping(value = "/{shopId}/queues",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    ResponseEntity<List<QueueWithOrdersInfo>> shopsShopIdQueuesGet(@PathVariable("shopId") Long shopId) {
        return null;
    }

}

