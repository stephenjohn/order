package com.eshop.order.service;

import com.eshop.order.dto.OrderDTO;
import com.eshop.order.entity.Order;
import com.eshop.order.entity.Status;
import com.eshop.order.repository.OrderRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    /**
     * retreive the order by ID
     * @param orderId
     * @return
     * @throws NotFoundException
     */

   public OrderDTO getOrderById(Long orderId) throws NotFoundException {
       Optional<Order> order = orderRepository.findById(orderId);
       if(order.isPresent()){
           return convertToDto(order.get());
       }else {
           throw new NotFoundException("Order not found with ID: "+ orderId);
       }

   }

    /**
     * retrieve the orders by userId
     * @param userId
     * @return List of orders
     * @throws NotFoundException
     */

   public List<OrderDTO> getAllOrderByUserId(Long userId) throws NotFoundException{
      List<Order> listOfOrderByUserId = orderRepository.findByUserId(userId);
      if (!listOfOrderByUserId.isEmpty()){
         return listOfOrderByUserId.stream().map(this::convertToDto)
                  .collect(Collectors.toList());

      }else {
          throw new NotFoundException("User ID not found");
      }
   }

   public Order convertToEntity(OrderDTO orderDTO) throws NotFoundException {
        Order order = new Order();
        order.setTotalPrice(orderDTO.getTotalPrice());
        Status status1 = Status.valueOf(orderDTO.getStatus());
        order.setStatus(status1.toString());
        order.setUserId(orderDTO.getUserId());
        return order;

    }

    public OrderDTO convertToDto(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getStatus().toString(),
                order.getUserId());

    }

}
