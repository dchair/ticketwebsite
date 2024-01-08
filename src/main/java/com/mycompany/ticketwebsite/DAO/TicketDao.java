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
    //新增一筆訂票資料
    public int saveTicket(TicketInfoModel ticket) {
        String sql2 = "INSERT INTO ticketinfo(dateandlocation, seat, tickettype, payment, collection) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql2, ticket.getDateandlocation(), ticket.getSeat(), ticket.getTickettype(), ticket.getPayment(), ticket.getCollection());
    }

    //查詢所有地點場次
    public List<TicketInfoModel> getTicketinfoByDateandlocation(String dateandlocation){
        return jdbcTemplate.query("SELECT * FROM ticketinfo where dateandlocation=?", new TicketMapper(), dateandlocation);
    }
    //查詢所有位置
    public List<TicketInfoModel> getTicketinfoBySeat(String seat){
        return jdbcTemplate.query("SELECT * FROM ticketinfo where seat=?", new TicketMapper(), seat);
    }


}
