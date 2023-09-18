package com.coffee.shop.service;

import com.coffee.shop.entity.Order;
import com.coffee.shop.model.OrderRequest;
import com.coffee.shop.model.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Long shopId , OrderRequest orderRequest);
    List<Order> getOrdersByShopId(Long shopId);

    OrderResponse getOrderById(Long orderId);
}
