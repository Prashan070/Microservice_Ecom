package com.ecom.order_service.dto;


import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private String CustomerId;
    private List<OrderItemRequestDTO> items;

}


