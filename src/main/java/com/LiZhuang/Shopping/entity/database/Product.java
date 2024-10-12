package com.LiZhuang.Shopping.entity.database;

import com.LiZhuang.Shopping.util.time.CustomDateTimeSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("product")
@Data
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String productIntroImages;
    private String productParaImages;
    private Integer stock;
    private String proPic = "default.jpg";
    private boolean isSwiper = false;
    private boolean isHot = false;
    private Integer swiperSort = 0;
    private String swiperPic = "default.jpg";
    private String description;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private Date hotDateTime;
}
