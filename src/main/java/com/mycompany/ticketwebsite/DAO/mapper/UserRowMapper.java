package com.mycompany.ticketwebsite.DAO.mapper;

import com.mycompany.ticketwebsite.model.UserRegModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserRegModel> {
    @Override
    public UserRegModel mapRow(ResultSet rs, int rowNum) throws SQLException{
        UserRegModel user= new UserRegModel();
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setMobile(rs.getString("mobile"));
        user.setEmail(rs.getString("e-mail"));
        return user;
    }
}
