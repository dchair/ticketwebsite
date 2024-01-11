package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.DAO.UserDao;
import com.mycompany.ticketwebsite.model.UserRegModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("usermodel", new UserRegModel());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("usermodel") UserRegModel user, Model model, HttpSession session){
        UserRegModel existingUser = userDao.findByUsername(user.getUsername());
        if(existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            // 登入成功, 將用戶訊息儲存在session
            session.setAttribute("user", existingUser);
            //跳轉回首頁
            return "redirect:/index";
        } else {
            // 登入失敗
            model.addAttribute("errmsg", "請檢查您的使用者名稱和密碼");
            return "login";
            // 重新導向到你的登入頁面
        }
    }
}
