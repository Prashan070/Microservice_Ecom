package com.ecom.order_service.repository;

import com.ecom.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<Order,Long> {
}
