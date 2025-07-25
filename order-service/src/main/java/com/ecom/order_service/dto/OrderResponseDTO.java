package com.ecom.order_service.dto;

import com.ecom.order_service.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDTO {

    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private OrderStatus status;
    private List<OrderItem> items;

}
