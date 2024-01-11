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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
    @ResponseBody
    public Map<String, Object> login(@ModelAttribute("usermodel") UserRegModel user, HttpSession session){
        Map<String, Object> response = new HashMap<>();
        UserRegModel existingUser = userDao.findByUsername(user.getUsername());
        if(existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            // 登入成功, 將用戶訊息儲存在session
            session.setAttribute("user", existingUser);
            // 設置登入成功的標誌
            response.put("success", true);
            // 設置需要重定向的 URL
            response.put("redirect", "/index");
        } else {
            // 登入失敗
            response.put("success", false);
            response.put("message", "請檢查您的使用者名稱和密碼");
        }

        return response;
    }
}
