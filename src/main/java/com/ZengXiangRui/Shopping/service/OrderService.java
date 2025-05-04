package com.ZengXiangRui.Shopping.service;

import com.ZengXiangRui.Shopping.entity.database.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {
    String getAllOrders();
    
    String getOrdersByUserId(String userId);
    
    String deleteOrder(String orderId);

    String createOrder(Order order);
} 