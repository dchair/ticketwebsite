package com.mycompany.ticketwebsite.DAO;

import com.mycompany.ticketwebsite.DAO.mapper.UserRowMapper;

import com.mycompany.ticketwebsite.model.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // 專門處理資料的 倉儲物件
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //新增會員資料
    public int saveUser(UserRegModel user){
        String sql1="INSERT INTO USER(username, password, mobile, `e-mail`) values (?,?,?,?)";
        return jdbcTemplate.update(sql1,user.getUsername(),user.getPassword(),user.getMobile(),user.getEmail());
    }
    //查詢密碼
    public UserRegModel findByUsername(String username){
        String sql3 = "SELECT * FROM USER where username=? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql3, new Object[]{username}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // 如果找不到匹配的數據，返回 null
            return null;
        }
    }
    //查詢帳號是否存在

    public long isUserExists(String username){
        String sql="SELECT COUNT (*) FROM USER WHERE username=?";
        return jdbcTemplate.queryForObject(sql,Long.class,username);
    }

    //更新密碼
    public void updatePassword(UserRegModel user){
        String sql4 = "UPDATE USER SET password=? WHERE username=?";
        jdbcTemplate.update(sql4, user.getPassword(), user.getUsername());
    }


}

