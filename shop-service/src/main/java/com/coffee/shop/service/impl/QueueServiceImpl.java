package com.coffee.shop.service.impl;

import com.coffee.shop.entity.Queue;
import com.coffee.shop.repository.QueueRepository;
import com.coffee.shop.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {
    private final QueueRepository queueRepository;

    @Override
    public Queue createQueue(Queue queue) {
        return queueRepository.save(queue);
    }

    @Override
    public List<Queue> getQueuesByShopId(Long shopId) {
        return queueRepository.findByShopId(shopId);
    }
}
