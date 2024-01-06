package com.eshop.order.dto;

import com.eshop.order.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private BigDecimal totalPrice;
    private String status;
    private Long userId;

}
