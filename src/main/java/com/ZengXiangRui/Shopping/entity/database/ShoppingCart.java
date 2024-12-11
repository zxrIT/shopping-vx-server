package com.ZengXiangRui.Shopping.entity.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ShoppingCart")
public class ShoppingCart {
    @TableId(type = IdType.ASSIGN_UUID)
    private Integer id;
    @TableField("userId")
    private String userId;
    private Integer product1;
    private Integer product2;
    private Integer product3;
    private Integer product4;
}
