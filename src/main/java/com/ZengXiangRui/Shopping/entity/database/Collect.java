package com.LiZhuang.Shopping.entity.database;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("collectTable")
@Data
public class Collect {
    private String id;
    private int product1;
    private int product2;
    private int product3;
    private int product4;
}
