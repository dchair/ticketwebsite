package com.mycompany.ticketwebsite.controller;

import com.mycompany.ticketwebsite.DAO.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @Autowired
    TicketDao ticketDao;
    @GetMapping("order-list")
        public String orderList(Model model){
        model.addAttribute("order", ticketDao.getTicketInfoModelAll());
        return "order-list";
    }
}
