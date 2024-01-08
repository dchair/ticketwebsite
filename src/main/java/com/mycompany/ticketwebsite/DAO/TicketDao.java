package com.mycompany.ticketwebsite.DAO;

import com.mycompany.ticketwebsite.model.TicketInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int saveTicket(TicketInfoModel ticket) {
        String sql2 = "INSERT INTO ticketinfo(dateandlocation, seat, tickettype, payment, collection) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql2, ticket.getDateandlocation(), ticket.getSeat(), ticket.getTickettype(), ticket.getPayment(), ticket.getCollection());
    }

}
