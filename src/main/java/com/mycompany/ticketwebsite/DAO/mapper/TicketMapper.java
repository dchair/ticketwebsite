package com.mycompany.ticketwebsite.DAO.mapper;

import com.mycompany.ticketwebsite.model.TicketInfoModel;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<TicketInfoModel> {
    @Override
    public TicketInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException{
        TicketInfoModel t= new TicketInfoModel();
        t.setDateandlocation(rs.getString("dateandlocation"));
        t.setPrice(rs.getString("price"));
        t.setPayment(rs.getString("payment"));
        t.setCollection(rs.getString("collection"));

        t.setUserid(rs.getString("userid"));
        return t;
    }
}

