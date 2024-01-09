package com.mycompany.ticketwebsite.DAO;

import com.mycompany.ticketwebsite.DAO.mapper.UserRowMapper;
import com.mycompany.ticketwebsite.model.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // 專門處理資料的 倉儲物件
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int saveUser(UserRegModel user){
        String sql1="INSERT INTO USER(username, password, mobile, `e-mail`) values (?,?,?,?)";
                return jdbcTemplate.update(sql1,user.getUsername(),user.getPassword(),user.getMobile(),user.getEmail());
    }

    public UserRegModel findByUsername(String username){
        String sql3="SELECT * FROM USER where username=?";
        return jdbcTemplate.queryForObject(sql3, new Object[]{username}, new UserRowMapper());
    }
    public long isUserExists(String username){
        String sql="SELECT COUNT (*) FROM USER WHERE username=?";
        return jdbcTemplate.queryForObject(sql,Long.class,username);
    }
}
