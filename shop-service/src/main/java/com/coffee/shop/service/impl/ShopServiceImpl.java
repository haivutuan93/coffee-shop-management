package com.coffee.shop.service.impl;

import com.coffee.shop.entity.Shop;
import com.coffee.shop.repository.ShopRepository;
import com.coffee.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop updateShop(Long shopId, Shop shop) {
        Shop existingShop = shopRepository.findById(shopId).orElseThrow(() -> new RuntimeException("Shop not found"));
        existingShop.setName(shop.getName());
        // Update other fields
        return shopRepository.save(existingShop);
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getShopById(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new RuntimeException("Shop not found"));
    }
}
