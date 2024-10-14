package com.LiZhuang.Shopping.service.impl;

import com.LiZhuang.Shopping.entity.database.Product;
import com.LiZhuang.Shopping.entity.response.BaseResponse;
import com.LiZhuang.Shopping.entity.response.product.ProductResponse;
import com.LiZhuang.Shopping.mapper.ProductMapper;
import com.LiZhuang.Shopping.service.ProductService;
import com.LiZhuang.Shopping.util.json.Json;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private Json json;

    @Override
    public String getHotProduct() {
        List<Product> productList = productMapper.selectList(
                new QueryWrapper<Product>()
                        .eq("productStatus", true)
                        .eq("isHot", true)
        );
        return json.toJson(new ProductResponse<List<Product>>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, productList
        ));
    }

    @Override
    public String getOtherProduct() {
        List<Product> productList = productMapper.selectList(new QueryWrapper<Product>()
                .eq("productStatus", true)
                .eq("isHot", false)
        );
        return json.toJson(new ProductResponse<List<Product>>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, productList
        ));
    }
}
