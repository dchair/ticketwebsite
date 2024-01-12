package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.model.CartItem;
import com.mycompany.ticketwebsite.model.ShoppingCart;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ShoppingCartService {

    private final ShoppingCart shoppingCart = new ShoppingCart();

    public void addToCart(ShoppingCart cart, String productId, int quantity) {
        // 實現加入購物車的邏輯
        // 更新 ShoppingCart 中的 cartItems
    }

    public void removeFromCart(ShoppingCart cart, String productId, int quantity) {
        // 實現從購物車中移除商品的邏輯
        // 更新 ShoppingCart 中的 cartItems
    }


    public void checkout(ShoppingCart cart) {

    }
}
