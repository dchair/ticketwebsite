package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.model.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    private final ShoppingCart shoppingCart = new ShoppingCart();

    public void addToCart(String productId, int quantity) {
        // 實現加入購物車的邏輯
        // 更新 ShoppingCart 中的 cartItems
    }

    public void removeFromCart(String productId, int quantity) {
        // 實現從購物車中移除商品的邏輯
        // 更新 ShoppingCart 中的 cartItems
    }

    public void checkout() {
        // 實現結帳的邏輯
        // 清空 ShoppingCart 中的 cartItems
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
