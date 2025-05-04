package com.ZengXiangRui.Shopping.entity.database;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("orders")
public class Order {
    private String id;
    
    @TableField("userId")
    private String userId;
    
    @TableField("totalAmount")
    private BigDecimal totalAmount;
    
    @TableField("orderStatus")
    private Integer orderStatus; // 0:待付款 1:已付款 2:已发货 3:已完成 4:已取消
    
    @TableField("createTime")
    private Date createTime;
    
    @TableField("updateTime")
    private Date updateTime;
    
    private String address;
    
    @TableField("contactPhone")
    private String contactPhone;
    
    @TableField("contactName")
    private String contactName;
} 