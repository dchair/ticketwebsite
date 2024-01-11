package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.model.UserRegModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        // 从会话中获取用户信息
        UserRegModel user = (UserRegModel) session.getAttribute("user");

        // 将用户信息传递给前端
        model.addAttribute("user", user);

        // 返回首页视图
        return "index";
    }
    @GetMapping("/concert-page/first")
    public String concertPageA() {
        return "concert-page-first";
    }
    @GetMapping("/concert-page/second")
    public String concertPageB() {
        return "concert-page-second";
    }
    @GetMapping("/concert-page/third")
    public String concertPageC() {
        return "concert-page-third";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart() {
        return "shopping-cart";
    }
    @GetMapping("/complete-purchase")
    public String completePurchase() {
        return "complete-purchase";
    }

}