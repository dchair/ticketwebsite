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
        return "complete-purchase";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        ShoppingCart cart = shoppingCartService.getOrCreateShoppingCart(session);
        model.addAttribute("cart", cart);
        return "shopping-cart";
    }

    @ModelAttribute("ticketModel")
    public TicketInfoModel getTicketModel() {
        return new TicketInfoModel();
    }


    @PostMapping("/addToCart")
    public String addToCart(@ModelAttribute("ticketModel") TicketInfoModel ticketModel,
                            RedirectAttributes redirectAttributes, HttpSession session) {
        shoppingCartService.addToCart(
                ticketModel.getDateandlocation(),
                ticketModel.getPrice(),
                ticketModel.getPayment(),
                ticketModel.getCollection(),
                ticketModel.getQuantity()
        );

        ShoppingCart cart = shoppingCartService.getOrCreateShoppingCart(session);
        redirectAttributes.addFlashAttribute("successMessage", "成功将商品添加到购物车！");
        return "redirect:/shopping/cart";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam String dateandlocation, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            shoppingCartService.removeFromCart(cart, dateandlocation);
        }
        return "redirect:/shopping/cart";
    }
}
