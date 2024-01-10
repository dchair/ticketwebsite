package com.mycompany.ticketwebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/concert-page")
    public String concertPage() {
        return "concert-page";
    }
    @GetMapping("/shopping-cart")
    public String shoppingCart() {
        return "shopping-cart";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/complete-purchase")
    public String completePurchase() {
        return "complete-purchase";
    }
    @GetMapping("/user-reg")
    public String userReg () {
        return "user-reg";
    }
    @GetMapping("/complete-reg")
    public String completeReg() {return "complete-reg";}
}

