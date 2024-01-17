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
    private OrderMapper orderMapper;


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
       Order order = orderMapper.convertToEntity(orderDTO);
       Order saveOrder = orderRepository.save(order);
       return orderMapper.convertToDto(saveOrder);
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
           return orderMapper.convertToDto(order.get());
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
         return listOfOrderByUserId.stream().map(e -> orderMapper.convertToDto(e))
                  .collect(Collectors.toList());

      }else {
          throw new NotFoundException("User ID not found");
      }
   }
}
