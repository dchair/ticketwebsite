package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.model.ShoppingCart;
import com.mycompany.ticketwebsite.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    @GetMapping("/checkout")
    public String checkout() {
        shoppingCartService.checkout();
        return "complete-purchase"; // 返回結帳成功的視圖
    }
    @GetMapping("/cart")
    public String viewCart(Model model) {
        ShoppingCart cart = shoppingCartService.getShoppingCart();
        model.addAttribute("cart", cart);
        return "shopping-cart"; // 返回顯示購物車的視圖
    }

}
