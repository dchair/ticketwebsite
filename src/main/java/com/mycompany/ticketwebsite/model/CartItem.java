package com.mycompany.ticketwebsite.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CartItem {
    private String productId;
    private String productName;
    private double price;
    private int quantity;

}

