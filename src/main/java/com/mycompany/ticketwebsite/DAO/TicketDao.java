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
        String sql2 = "INSERT INTO ticketinfo(dateandlocation, tickettype, payment, collection, userid) values(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql2, ticket.getDateandlocation(), ticket.getTickettype(), ticket.getPayment(), ticket.getCollection(), ticket.getUserid());
    }

    //查詢所有訂票資訊
    public List<TicketInfoModel> getTicketInfoModelAll(){
        return jdbcTemplate.query("SELECT * FROM ticketinfo", new TicketMapper());
    }
    //查詢所有地點場次
    public List<TicketInfoModel> getTicketinfoByDateandlocation(String dateandlocation){
        return jdbcTemplate.query("SELECT * FROM ticketinfo where dateandlocation=?", new TicketMapper(), dateandlocation);
    }

}
