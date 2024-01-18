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


    private String dateandlocation;
    //演唱會地點場次 1.1/13台北兩廳院 2.1/13台中歌劇院 3.1/13高雄衛武營 4.1/20台北兩廳院 5.1/20台中歌劇院 6.1/20高雄衛武營
    private String price;
    //票種 1.敬老票 2.學生票 3.全票
    private int quantity;
    private String payment;
    //支付方式 1.信用卡 2.超商付款 3.轉帳
    private String collection;
    //取票方式 1.超商取票 2.現場取票 3.電子票券
    private String userid; // 初始化 userid



}

