package com.LiZhuang.Shopping.entity.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ProductType")
public class ProductType {
    @TableId(type = IdType.ASSIGN_UUID)
    private int id;

    @TableField("typeName")
    private String typeName;

    @TableField("typeImage")
    private String typeImage;
}
