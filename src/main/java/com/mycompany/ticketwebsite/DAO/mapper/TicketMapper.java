package com.mycompany.ticketwebsite.DAO.mapper;

import com.mycompany.ticketwebsite.model.TicketInfoModel;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements RowMapper<TicketInfoModel> {
    @Override
    public TicketInfoModel mapRow(ResultSet rs, int rowNum) throws SQLException{
        TicketInfoModel t= new TicketInfoModel();
        t.setDateandlocation(rs.getShort("dateandlocation"));
        t.setTickettype(rs.getShort("tickettype"));
        t.setPayment(rs.getShort("payment"));
        t.setCollection(rs.getShort("collection"));

        t.setUserid(rs.getShort("userid"));
        return t;
    }
}

