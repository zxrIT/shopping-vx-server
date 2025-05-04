package com.ZengXiangRui.Shopping.service.impl;

import com.ZengXiangRui.Shopping.entity.database.Order;
import com.ZengXiangRui.Shopping.entity.response.BaseResponse;
import com.ZengXiangRui.Shopping.entity.response.order.OrderResponse;
import com.ZengXiangRui.Shopping.mapper.OrderMapper;
import com.ZengXiangRui.Shopping.service.OrderService;
import com.ZengXiangRui.Shopping.util.json.Json;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private Json json;
    
    @Override
    public String getAllOrders() {
        try {
            List<Order> orders = orderMapper.selectList(new QueryWrapper<>());
            return json.toJson(new OrderResponse<List<Order>>(
                    BaseResponse.SUCCESS_CODE,
                    BaseResponse.SUCCESS_MESSAGE,
                    orders
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return json.toJson(new OrderResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "获取订单列表失败：" + e.getMessage()
            ));
        }
    }
    
    @Override
    public String getOrdersByUserId(String userId) {
        try {
            List<Order> orders = orderMapper.selectList(
                new QueryWrapper<Order>().eq("userId", userId)
            );
            return json.toJson(new OrderResponse<List<Order>>(
                    BaseResponse.SUCCESS_CODE,
                    BaseResponse.SUCCESS_MESSAGE,
                    orders
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return json.toJson(new OrderResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "获取用户订单失败：" + e.getMessage()
            ));
        }
    }
    
    @Override
    public String deleteOrder(String orderId) {
        try {
            Order existingOrder = orderMapper.selectById(orderId);
            if (existingOrder == null) {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "订单不存在"
                ));
            }
            
            int result = orderMapper.deleteById(orderId);
            if (result > 0) {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.SUCCESS_CODE,
                        BaseResponse.SUCCESS_MESSAGE,
                        "删除成功"
                ));
            } else {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "删除失败"
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return json.toJson(new OrderResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "删除订单失败：" + e.getMessage()
            ));
        }
    }

    @Override
    public String createOrder(Order order) {
        try {
            // 参数验证
            if (order.getUserId() == null || order.getUserId().isEmpty()) {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "用户ID不能为空"
                ));
            }
            if (order.getTotalAmount() == null) {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "订单金额不能为空"
                ));
            }
            if (order.getAddress() == null || order.getAddress().isEmpty()) {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "收货地址不能为空"
                ));
            }
            if (order.getContactPhone() == null || order.getContactPhone().isEmpty()) {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "联系电话不能为空"
                ));
            }
            if (order.getContactName() == null || order.getContactName().isEmpty()) {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "联系人不能为空"
                ));
            }

            // 设置订单基本信息
            order.setId(UUID.randomUUID().toString());
            order.setOrderStatus(0); // 设置初始状态为待付款
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());

            // 保存订单
            int result = orderMapper.insert(order);
            if (result > 0) {
                return json.toJson(new OrderResponse<Order>(
                        BaseResponse.SUCCESS_CODE,
                        BaseResponse.SUCCESS_MESSAGE,
                        order
                ));
            } else {
                return json.toJson(new OrderResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "创建订单失败"
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return json.toJson(new OrderResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "创建订单失败：" + e.getMessage()
            ));
        }
    }
} 