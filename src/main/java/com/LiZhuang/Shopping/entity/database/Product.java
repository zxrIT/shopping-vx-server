package com.LiZhuang.Shopping.entity.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("product")
@Data
public class Product {
    @TableId(type = IdType.ASSIGN_UUID)
    private int id;

    @TableField("productName")
    private String productName;

    @TableField(value = "productStatus", exist = false)
    private boolean productStatus;

    @TableField(value = "isHot", exist = false)
    private boolean isHot;
    private boolean collect;
    private int price;

    @TableField("productImage")
    private String productImage;

    @TableField("productType")
    private int productType;

    @TableField("productImageChoice")
    private String productImageChoice;

    @TableField("originalPrice")
    private int originalPrice;

    private String description;
}
