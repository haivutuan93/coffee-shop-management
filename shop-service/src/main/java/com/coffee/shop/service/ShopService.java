package com.coffee.shop.service;

import com.coffee.shop.entity.Shop;

import java.util.List;

public interface ShopService {
    Shop createShop(Shop shop);
    Shop updateShop(Long shopId, Shop shop);
    List<Shop> getAllShops();
    Shop getShopById(Long shopId);
}
