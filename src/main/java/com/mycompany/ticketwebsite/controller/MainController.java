package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.model.UserRegModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        // 從會話獲取user資料
        UserRegModel user = (UserRegModel) session.getAttribute("user");

        // 將用戶訊息傳給前端
        model.addAttribute("user", user);

        // 返回首頁
        return "index";
    }

    @GetMapping("/concert-page/first")
    public String concertPageA(Model model, HttpSession session) {
        model.addAttribute("usermodel", new UserRegModel());
        // 從會話獲取user資料
        UserRegModel user = (UserRegModel) session.getAttribute("user");

        // 將用戶訊息傳給前端
        model.addAttribute("user", user);
        if (user != null)
        {
        return "concert-page-first";
    }
        else
        {
        return "redirect:/alert-page";}
    }

    @GetMapping("/concert-page/second")
    public String concertPageB(Model model, HttpSession session) {
        model.addAttribute("usermodel", new UserRegModel());
        // 從會話獲取user資料
        UserRegModel user = (UserRegModel) session.getAttribute("user");

        // 將用戶訊息傳給前端
        model.addAttribute("user", user);
        if (user != null)
        {
            return "concert-page-second";
        }
        else
        {
            return "redirect:/alert-page";}
    }


    @GetMapping("/concert-page/third")
    public String concertPageC(Model model, HttpSession session) {

        model.addAttribute("usermodel", new UserRegModel());
        // 從會話獲取user資料
        UserRegModel user = (UserRegModel) session.getAttribute("user");

        // 將用戶訊息傳給前端
        model.addAttribute("user", user);
        if (user != null)
        {
            return "concert-page-third";
        }
        else
        {
            return "redirect:/alert-page";}
    }


    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, HttpSession session) {
        // 从会话中获取用户信息
        UserRegModel user = (UserRegModel) session.getAttribute("user");

        // 将用户信息传递给前端
        model.addAttribute("user", user);
        return "shopping-cart";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "index";
    }

    @GetMapping("/complete-reg")
    public String completeReg() {
        return "complete-reg";
    }

    @GetMapping("/agreement")
    public String agreement() {return "agreement";}
}