package com.LiZhuang.Shopping.entity.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("address")
public class Address {
    @TableField("tableId")
    @TableId(type = IdType.AUTO)
    private int tableId;
    private String id;
    @TableField("addressId")
    private int addressId;
    @TableField("addressContent")
    private String addressContent;
    @TableField("username")
    private String username;
    private String sex;
    private String mobile;
    private String topic;
}
