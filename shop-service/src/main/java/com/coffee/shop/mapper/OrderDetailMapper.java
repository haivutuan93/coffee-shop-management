package com.coffee.shop.mapper;

import com.coffee.shop.entity.OrderDetail;
import com.coffee.shop.model.OrderDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailMapper {
    public List<OrderDetailResponse> toDTOs(List<OrderDetail> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDetailResponse toDTO(OrderDetail entity) {
        return OrderDetailResponse.builder()
                .shopProductId(entity.getShopProductId())
                .quantity(entity.getQuantity())
                .build();
    }
}

