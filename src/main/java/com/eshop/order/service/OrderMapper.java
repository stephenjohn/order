package com.eshop.order.service;

import com.eshop.order.dto.OrderDTO;
import com.eshop.order.entity.Order;
import com.eshop.order.entity.Status;
import com.eshop.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    @Autowired
    private OrderRepository orderRepository;

    /**
     * method to convertToEntity
     * @param orderDTO
     * @return
     */
    public Order convertToEntity(OrderDTO orderDTO){
        try {
            Order order = Order.builder()
                    .totalPrice(orderDTO.getTotalPrice())
                    .status(Status.valueOf(orderDTO.getStatus()).name())
                    .userId(orderDTO.getUserId())
                    .build();

            return order;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage() + " please use these status : "  +  Status.PENDING.name()  + " , "+  Status.COMPLETED.name() +" , " +  Status.SHIPPED.name());
        }
    }

    /**
     * method to convertToDto
     * @param order
     * @return
     */
    public OrderDTO convertToDto(Order order){
        OrderDTO orderDTO = OrderDTO.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus().toString())
                .userId(order.getUserId())
                .build();
        return orderDTO;
    }



}
