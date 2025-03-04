package com.ZengXiangRui.Shopping.entity.database;

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

    @TableField("productStatus")
    private boolean productStatus;

    @TableField("isHot")
    private boolean isHot;
    private boolean collect;
    private int price;

    @TableField("flowerLanguage")
    private String flowerLanguage;

    @TableField("productImage")
    private String productImage;

    @TableField("productType")
    private int productType;

    @TableField("productImageChoice")
    private String productImageChoice;

    private int images;

    @TableField("originalPrice")
    private int originalPrice;

    private String description;
}
