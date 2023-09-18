package com.coffee.shop.repository;

import com.coffee.shop.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, Long> {
    List<Queue> findByShopId(Long shopId);
    List<Queue> findByShopIdOrderByCurrentQuantityOrdersAsc(Long shopId);
}
