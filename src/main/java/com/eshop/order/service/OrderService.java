package com.eshop.order.service;

import com.eshop.order.dto.OrderDTO;
import com.eshop.order.entity.Order;
import com.eshop.order.entity.Status;
import com.eshop.order.repository.OrderRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    /**
     * method to save the order
     * @param orderDTO
     * @return
     * @throws NotFoundException
     */
    @Transactional
   public OrderDTO saveOrder(OrderDTO orderDTO) throws NotFoundException {
       Order order = convertToEntity(orderDTO);
       Order saveOrder = orderRepository.save(order);
       return convertToDto(saveOrder);
   }

   private Order convertToEntity(OrderDTO orderDTO) throws NotFoundException {
        Order order = new Order();
        order.setTotalPrice(orderDTO.getTotalPrice());
        Status status1 = Status.valueOf(orderDTO.getStatus());
        order.setStatus(status1.toString());
        order.setUserId(orderDTO.getUserId());
        return order;

    }

    private OrderDTO convertToDto(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getStatus().toString(),
                order.getUserId());

    }

}
