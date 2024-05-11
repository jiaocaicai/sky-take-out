package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/**
 * ClassName: ShoppingCartService
 * Package: com.sky.service
 * Description:
 *
 * @Author 陈玉佼
 * @Create 2024/5/11 16:57
 * @Version 1.0
 */
public interface ShoppingCartService {
    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> showShoppingCart();

    void cleanShoppingCart();
}
