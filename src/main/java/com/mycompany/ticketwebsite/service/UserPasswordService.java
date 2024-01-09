package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.DAO.UserDao;
import com.mycompany.ticketwebsite.model.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordService {
    @Autowired
    private UserDao userDao;
    public int changePassword(String username, String oldPassword, String newPassword){
        // 根據使用者名稱取得使用者資料
        UserRegModel user= userDao.findByUsername(username);
        // 驗證舊密碼
        if(user!= null && user.getPassword().equals(oldPassword)) {
            // 更新密碼
            user.setPassword(newPassword);
            userDao.updatePassword(user);
            return 1; // 密碼修改成功
        }else{
            return 2; // 舊密碼不正確
        }
    }
}
