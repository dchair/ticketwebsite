package com.mycompany.ticketwebsite.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderModel {
    String concertName;
    String dateandlocation;
    String price;
    int quantity;
    String payment;
    String collection;
    String userid;
}
