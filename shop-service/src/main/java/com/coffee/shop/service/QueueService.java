package com.coffee.shop.service;

import com.coffee.shop.entity.Queue;

import java.util.List;

public interface QueueService {
    Queue createQueue(Queue queue);
    List<Queue> getQueuesByShopId(Long shopId);
}
