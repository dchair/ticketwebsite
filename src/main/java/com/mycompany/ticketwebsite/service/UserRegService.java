package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.DAO.UserDao;
import com.mycompany.ticketwebsite.model.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/*
   1. 檢查使用者是否已經註冊過
   2. 檢查帳號是否已經停用
   3. 檢查帳號是否已經被使用過
 */
public class UserRegService {
    @Autowired
    UserDao userDao;
    public int Registration (UserRegModel user){
        // 1:成功 , 2: 帳號已經存在 3: 包含惡意或是禁用字串
        // 收到註冊需求  先檢查帳號是否存在
        // 存在  退申請
        if(user.getUsername().contains("select") || user.getUsername().contains("delete")){
            return 3;
        }
        if(isUserExists(user.getUsername())){
            return 2;
        }
        // 不存在  開始檢查資料是否合規
        // 過濾 惡意字詞 可能異常字串(select , insert, update, delete 等)
        // 開始進行 帳號新增作業
        int cnt = userDao.saveUser(user);
        if(cnt>0){
            return 1;
        }else{
            return cnt;
        }
    }
    // 獨立寫成一個公用方法 讓確認帳號是否存在的功能可以分享 , 改密碼也需要檢查是有此帳號
    public boolean isUserExists(String username){
        long count = userDao.isUserExists(username);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
}
