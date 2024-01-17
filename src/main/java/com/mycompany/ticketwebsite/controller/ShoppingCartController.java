package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.model.ShoppingCart;
import com.mycompany.ticketwebsite.model.TicketInfoModel;
import com.mycompany.ticketwebsite.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

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
        ShoppingCart cart = shoppingCartService.getOrCreateShoppingCart(session);
        model.addAttribute("cart", cart);
        return "shopping-cart";
    }

    @ModelAttribute("ticketInfoModel")
    public TicketInfoModel ticketInfoModel() {
        TicketInfoModel model = new TicketInfoModel();
        System.out.println("Creating ticketInfoModel: " + model);
        return model;
    }

    @PostMapping("/addToCart")
    public String addToCart(@ModelAttribute TicketInfoModel ticketInfoModel,
                            RedirectAttributes redirectAttributes, Model model, HttpSession session) {

        // 添加购物车
        shoppingCartService.addToCart(ticketInfoModel.getDateandlocation(), ticketInfoModel.getPrice(), ticketInfoModel.getPayment(), ticketInfoModel.getCollection(), ticketInfoModel.getQuantity());
        // 添加 cart 和 productNames 到模型中
        model.addAttribute("cart", shoppingCartService.getOrCreateShoppingCart(session));
        // 重定向到购物车页面
        redirectAttributes.addFlashAttribute("successMessage", "成功将商品添加到购物车！");

        return "redirect:/shopping/cart";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam String dateandlocation, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            shoppingCartService.removeFromCart(cart, dateandlocation);
        }
        return "redirect:/shopping-cart";
    }
    // 當用戶提交從購物車移除商品的表單時，這個方法會從 Session 中獲取購物車，如果存在則調用


    // 添加一個方法來獲取或創建 ShoppingCart
    private static final String CART_SESSION_ATTRIBUTE = "cart";

    private synchronized ShoppingCart getOrCreateShoppingCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(CART_SESSION_ATTRIBUTE);
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute(CART_SESSION_ATTRIBUTE, cart);
        }
        return cart;
    }


    }

