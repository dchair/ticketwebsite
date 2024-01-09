package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.DAO.UserDao;
import com.mycompany.ticketwebsite.model.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;

    @PostMapping("/login")
    public String login(String username, String password, Model model){
        UserRegModel user = userDao.findByUsername(username);
        if(user!=null && user.getPassword().equals(password)) {
            //登入成功
            return "redirect://home";
        }else{
            //登入失敗
            model.addAttribute("errmsg", "請檢查您的使用者名稱和密碼");
            return "login";
            // 重新導向到你的登入頁面
        }
    }
}
