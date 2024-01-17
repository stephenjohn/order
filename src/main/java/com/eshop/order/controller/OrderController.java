package com.eshop.order.controller;

import com.eshop.order.dto.OrderDTO;
import com.eshop.order.service.OrderService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO)throws NotFoundException{
        return orderService.saveOrder(orderDTO);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById (@PathVariable Long orderId) throws NotFoundException {
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderDTO);
    }
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrderByUserId(@PathVariable Long userId) throws NotFoundException {
        List<OrderDTO> listOfOrder = orderService.getAllOrderByUserId(userId);
        return ResponseEntity.ok(listOfOrder);
    }
}
