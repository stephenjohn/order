package com.eshop.order.service;

import com.eshop.order.dto.OrderDTO;
import com.eshop.order.entity.Order;
import com.eshop.order.repository.OrderRepository;
import javassist.NotFoundException;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private OrderMapper orderMapper;

    @BeforeEach
    void setup(){
        orderRepository.deleteAll();
    }

    @Test
    void testSaveOrder()throws NotFoundException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus("PENDING");
        orderDTO.setTotalPrice(BigDecimal.valueOf(10.99));
        orderDTO.setUserId(1L);


//        when(orderMapper.convertToEntity(orderDTO)).thenReturn(orderEntity);
//        when(orderRepository.save(orderEntity)).thenReturn(saveEntity);
//        when(orderMapper.convertToDto(saveEntity)).thenReturn(orderDTO);

        OrderDTO result = orderService.saveOrder(orderDTO);

        assertEquals(orderDTO.getStatus(),result.getStatus());
        assertEquals(orderDTO.getTotalPrice(),result.getTotalPrice());
        assertEquals(orderDTO.getUserId(),result.getUserId());
        assertNotNull(result.getId());

    }
    @Test
    void testGetOrderById()throws NotFoundException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus("PENDING");
        orderDTO.setTotalPrice(BigDecimal.valueOf(10.99));
        orderDTO.setUserId(1L);

//        when(orderRepository.findById(1L))
//                .thenReturn(Optional.of(order));

        OrderDTO orderDTO1 = orderService.saveOrder(orderDTO);
        OrderDTO result = orderService.getOrderById(orderDTO1.getId());

        assertEquals(result, orderDTO1);
    }
    @Test
    void testGetAllOrderByUserId()throws NotFoundException {
        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setStatus("COMPLETED");
        orderDTO1.setTotalPrice(BigDecimal.valueOf(20.99));
        orderDTO1.setUserId(1L);

        OrderDTO orderDTO2 = new OrderDTO();
        orderDTO2.setStatus("SHIPPED");
        orderDTO2.setTotalPrice(BigDecimal.valueOf(30.99));
        orderDTO2.setUserId(1L);

        List<OrderDTO> result = new ArrayList<>();
        result.add(orderDTO1);
        result.add(orderDTO2);

//       Mockito.when(orderRepository.findByUserId(1L))
//               .thenReturn(orders);
        orderService.saveOrder(orderDTO1);
        orderService.saveOrder(orderDTO2);
        List<OrderDTO> allResult = orderService.getAllOrderByUserId(1L);

        assertEquals(allResult.size(),result.size());
        assertNotNull(allResult.get(0).getId());
        assertEquals(allResult.get(0).getUserId(), result.get(0).getUserId());

        for(OrderDTO orderDTO : allResult){
            assertEquals(orderDTO.getUserId(),1);
        }
    }
}
