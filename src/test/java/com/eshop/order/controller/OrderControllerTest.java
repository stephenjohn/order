package com.eshop.order.controller;

import com.eshop.order.dto.OrderDTO;
import com.eshop.order.entity.Order;
import com.eshop.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService  orderService;

    private Order order;
    private OrderDTO orderDTO;
    @BeforeEach
    public void setup(){



    }
}
