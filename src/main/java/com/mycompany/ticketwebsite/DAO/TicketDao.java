package com.mycompany.ticketwebsite.DAO;

import com.mycompany.ticketwebsite.DAO.mapper.TicketMapper;
import com.mycompany.ticketwebsite.model.TicketInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 新增一筆訂票資料
    public int saveTicket(TicketInfoModel ticket) {
        String sql = "INSERT INTO ticketinfo(concertName, dateandlocation, price, quantity, payment, collection, userid) VALUES(?, ?, ?, ?, ?, ?, ?)";
        System.out.println("Executing SQL: " + sql);
        return jdbcTemplate.update(sql, ticket.getConcertName(), ticket.getDateandlocation(), ticket.getPrice(), ticket.getQuantity(), ticket.getPayment(), ticket.getCollection(), ticket.getUserid());
    }

    // 查詢所有訂票資訊
    public List<TicketInfoModel> getTicketInfoModelAll() {
        return jdbcTemplate.query("SELECT * FROM ticketinfo", new TicketMapper());
    }

    // 根據日期和地點查詢訂票資訊
    public List<TicketInfoModel> getTicketinfoByDateandlocation(String dateandlocation) {
        return jdbcTemplate.query("SELECT * FROM ticketinfo WHERE dateandlocation=?", new TicketMapper(), dateandlocation);
    }

    // 其他可能的數據庫操作...

}
