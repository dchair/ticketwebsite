package com.mycompany.ticketwebsite.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TicketInfoModel {
    private int dateandlocation;
    //演唱會地點場次 1... 2... 3...4... 5... 6... 三場演唱會各兩場台北and高雄
    private String seat;
    //座位
    private int tickettype;
    //票種 1... 2... 3...
    private int payment;
    //支付方式 1... 2... 3...
    private int collection;
    //取票方式 1... 2... 3...
}