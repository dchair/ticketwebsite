package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.model.UserRegModel;
import com.mycompany.ticketwebsite.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    UserRegService userRegService;

    @GetMapping("/user-regform")
    public String UserReg(Model model){
        // 使用者送 request 進來後 回傳 註冊表單名稱
        // 準備好一個空的 UserRegModel 物件 提供表單與 UserRegModel 綁定
        UserRegModel userModel = new UserRegModel();
        model.addAttribute("usermodel",userModel);
        return "user-reg";
    }
    @PostMapping("/user-regform2")
    public String UserRegProcess2(@ModelAttribute UserRegModel usermodel, Model model) {
        String msg = null;
        // 使用者送 request 進來後 回傳 註冊表單名稱
        // 呼叫 service 開始進行新增
        int result = userRegService.Registration(usermodel);
        switch (result){
            case 0:
                msg="新增失敗";
                break;
            case 1:
                msg="您的帳號已成功註冊";
                break;
            case 2:
                msg="您的帳號已經在本系統註冊過,請使用登入功能";
                break;
            case 3:
                msg="帳號不可包含系統禁止關鍵字(select,insert,update,delete等 或是惡意字詞)";
                break;
            default:
                msg="其他原因 請聯絡本站管理人員";
                break;
        }
        //結果通知
        model.addAttribute("user", usermodel.getUsername());
        model.addAttribute("mesg", msg);
        return "reg-result";
    }
    @GetMapping("/user-reg")
    public String register(UserRegModel user, Model model) {
        // 檢查兩次密碼是否相符
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "密碼不一致");
            model.addAttribute("usermodel", user); // 保留用戶輸入的其他資訊
            return "user-reg"; // 返回註冊頁面
        } else {
            int result = userRegService.Registration(user);
            return "redirect:/concert-page";// 假設註冊成功後轉向其他頁面
        }
    }

}

