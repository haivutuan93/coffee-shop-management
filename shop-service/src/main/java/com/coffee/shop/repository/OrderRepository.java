package com.coffee.shop.repository;

import com.coffee.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByShopId(Long shopId);
}
