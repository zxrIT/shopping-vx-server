package com.ZengXiangRui.Shopping.entity.database;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private String id;
    private String username;
    private String account;
    private String password;
    
    @TableField("roleId")
    private Integer roleId;
    
    @TableField("userIcon")
    private String userIcon;

    private String token;
    
    @TableField("shoppingCart")
    private String shoppingCart;
    
    @TableField("collect")
    private String collect;
    
    @TableField("address")
    private String address;
}
