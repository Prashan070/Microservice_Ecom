package com.ecom.order_service.entity;

import com.ecom.order_service.dto.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private OrderStatus status;


}
