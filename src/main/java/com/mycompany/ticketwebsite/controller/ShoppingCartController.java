package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.model.ShoppingCart;
import com.mycompany.ticketwebsite.model.TicketInfoModel;
import com.mycompany.ticketwebsite.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        return "complete-purchase"; // 這個方法會檢查 Session 中的購物車，並調用@service完成結帳操作。
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "shopping-cart";
        //  當用戶訪問購物車頁面時，這個方法會檢查 Session 中的購物車，如果不存在則創建一個新的購物車。
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam int dateandlocation,
                            @RequestParam String price,
                            @RequestParam String payment,
                            @RequestParam String collection,
                            @RequestParam int quantity,
                            RedirectAttributes redirectAttributes) {
        // 在這裡處理表單提交，例如將參數放入購物車

        // 假設有一個購物車服務類，將商品信息添加到購物車
        shoppingCartService.addToCart(dateandlocation, price, payment, collection, quantity);

        // 重定向到購物車頁面
        redirectAttributes.addFlashAttribute("successMessage", "成功將商品添加到購物車！");
        return "redirect:/shopping-cart";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam int dateandlocation, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            shoppingCartService.removeFromCart(cart, dateandlocation);
        }
        return "redirect:/shopping-cart";
    }
    // 當用戶提交從購物車移除商品的表單時，這個方法會從 Session 中獲取購物車，如果存在則調用


    // 添加一個方法來獲取或創建 ShoppingCart
    private ShoppingCart getOrCreateShoppingCart(HttpSession session) {
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
    if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("cart", cart);
    }
    return cart;
    }
}
