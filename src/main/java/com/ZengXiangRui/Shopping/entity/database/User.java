package com.ZengXiangRui.Shopping.entity.database;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private String id;
    private String username;
    private String token;
    private String address;
    private String collect;
    private String account;
    private String password;
    @TableField("userIcon")
    private String userIcon;
    @TableField("roleId")
    private Integer roleId;
    @TableField("shoppingCart")
    private String shoppingCart;
}
