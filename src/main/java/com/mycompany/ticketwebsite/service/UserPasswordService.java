package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.DAO.UserDao;
import com.mycompany.ticketwebsite.model.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordService {
    @Autowired
    private UserDao userDao;

    // 定義常數代表不同的修改密碼結果
    private static final int PASSWORD_CHANGE_SUCCESS = 1;
    private static final int PASSWORD_CHANGE_FAILURE_INCORRECT_OLD = 2;
    private static final int PASSWORD_CHANGE_FAILURE_OTHER_REASON = 3;

    public int changePassword(String username, String oldPassword, String newPassword){
        // 根據使用者名稱取得使用者資料
        UserRegModel user = userDao.findByUsername(username);

        // 驗證舊密碼
        if (user != null && user.getPassword().equals(oldPassword)) {
            try {
                // 使用者存在，並且舊密碼正確，更新密碼
                user.setPassword(newPassword);
                userDao.updatePassword(user);
                return PASSWORD_CHANGE_SUCCESS; // 密碼修改成功
            } catch (Exception e) {
                e.printStackTrace();
                return PASSWORD_CHANGE_FAILURE_OTHER_REASON; // 修改失敗，其他原因
            }
        } else {
            return PASSWORD_CHANGE_FAILURE_INCORRECT_OLD; // 舊密碼不正確
        }
    }
}
