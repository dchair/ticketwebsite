package com.mycompany.ticketwebsite.service;

import com.mycompany.ticketwebsite.DAO.mapper.TicketMapper;
import com.mycompany.ticketwebsite.model.TicketInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderService {

    JdbcTemplate jdbcTemplate;
    public List<TicketInfoModel> getTicketInfoModelAll() {
        return jdbcTemplate.query("SELECT * FROM ticketinfo", new TicketMapper());
    }
}
