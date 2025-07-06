package com.ecom.order_service.service;

import com.ecom.order_service.dto.*;
import com.ecom.order_service.entity.Order;
import com.ecom.order_service.entity.OrderItem;
import com.ecom.order_service.repository.OrderItemRepository;
import com.ecom.order_service.repository.OrderRespository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private OrderRespository orderRespository;
    private OrderItemRepository orderItemRepository;
    private ProductClient productClient;

    public OrderService(OrderRespository orderRespository, OrderItemRepository orderItemRepository, ProductClient productClient) {
        this.orderRespository = orderRespository;
        this.orderItemRepository = orderItemRepository;
        this.productClient = productClient;
    }

    public OrderResponseDTO placeOrder(OrderRequestDTO requestDto) {

        /*
       CustomerId;
    items{
     productId;
    quantity;
    }
*/

/*  private String orderId;
    private String customerId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private OrderStatus status;
    private List<OrderItem> items;

 */
        String orderid = generateOrderId();

        double totalAmount = 0.0;

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequestDTO itemRequest : requestDto.getItems()) {
            ProductResponseDTO product = productClient.getProductName(itemRequest.getProductId());

            if (product.getStockQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficent stock for product" + product.getName());
            }

            productClient.updateStock(itemRequest.getProductId(), -itemRequest.getQuantity());

            double itemTotal = itemRequest.getQuantity() * product.getPrice();
            totalAmount += itemTotal;

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemId(generateOrderItemId());
            orderItem.setOrderId(orderid);
            orderItem.setProductId(itemRequest.getProductId());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());


            orderItems.add(orderItem);


        }
        // Save order
        Order order = new Order(orderid, requestDto.getCustomerId(), LocalDateTime.now(), totalAmount, OrderStatus.PENDING);
        orderRespository.save(order);
        orderItemRepository.saveAll(orderItems);

        return new OrderResponseDTO(order.getOrderId(), order.getCustomerId(), order.getOrderDate(), order.getTotalAmount()
                , order.getStatus(), orderItems);


    }

    private String generateOrderId() {
        return "ord-" + UUID.randomUUID().toString().substring(0, 8);

    }

    private String generateOrderItemId() {
        return "item-" + UUID.randomUUID().toString().substring(0, 8);

    }

}
