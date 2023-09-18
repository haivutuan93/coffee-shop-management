package com.coffee.shop.repository;

import com.coffee.shop.entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopProductRepository extends JpaRepository<ShopProduct, Long> {
    List<ShopProduct> findByIdIn(List<Long> ids);
}
