package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.model.ShoppingCart;
import com.mycompany.ticketwebsite.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/checkout")
    public String checkout(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            shoppingCartService.checkout(cart);
            session.removeAttribute("cart");
        }

        return "complete-purchase"; // 這個方法會檢查 Session 中的購物車，並調用@service完成結帳操作。
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart);
        return "shopping-cart";
        //  當用戶訪問購物車頁面時，這個方法會檢查 Session 中的購物車，如果不存在則創建一個新的購物車。
    }
    @PostMapping("/addToCart")
    public String addToCart(@RequestParam String productId, @RequestParam int quantity, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        shoppingCartService.addToCart(cart, productId, quantity);
        return "redirect:/shopping-cart";
        // 當用戶提交添加商品到購物車的表單時，這個方法會從 Session 中獲取購物車，如果不存在則創建一個新的購物車。然後調用@Service將商品添加到購物車
        // 最後，通過重定向(刷新當前頁面)重新導向到購物車頁面。

    }
    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam String productId, @RequestParam int quantity, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            shoppingCartService.removeFromCart(cart, productId, quantity);
        }
        return "redirect:/shopping-cart"; // 重新導向購物車頁面
    }
    // 當用戶提交從購物車移除商品的表單時，這個方法會從 Session 中獲取購物車，如果存在則調用
}
