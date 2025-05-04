package com.ZengXiangRui.Shopping.controller;

import com.ZengXiangRui.Shopping.entity.database.Order;
import com.ZengXiangRui.Shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/getAllOrders")
    public String getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @GetMapping("/getOrdersByUserId/{userId}")
    public String getOrdersByUserId(@PathVariable String userId) {
        return orderService.getOrdersByUserId(userId);
    }
    
    @DeleteMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable String orderId) {
        return orderService.deleteOrder(orderId);
    }

    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
} 