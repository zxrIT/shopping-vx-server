package com.ZengXiangRui.Shopping.service.impl;

import com.ZengXiangRui.Shopping.entity.database.Product;
import com.ZengXiangRui.Shopping.entity.database.ProductType;
import com.ZengXiangRui.Shopping.entity.response.BaseResponse;
import com.ZengXiangRui.Shopping.entity.response.product.ProductResponse;
import com.ZengXiangRui.Shopping.mapper.ProductMapper;
import com.ZengXiangRui.Shopping.mapper.ProductTypeMapper;
import com.ZengXiangRui.Shopping.service.ProductService;
import com.ZengXiangRui.Shopping.util.json.Json;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    private ProductTypeMapper productTypeMapper;

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
                .last("limit 8")
        );
        return json.toJson(new ProductResponse<List<Product>>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, productList
        ));
    }

    @Override
    public String getProductType() {
        List<ProductType> productTypeList = productTypeMapper.selectList(
                new QueryWrapper<ProductType>()
        );
        return json.toJson(new ProductResponse<List<ProductType>>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, productTypeList));
    }

    @Override
    public String getProduct(int typeId) {
        List<Product> productList = null;
        if (typeId == 1000) {
            productList = productMapper.selectList(new QueryWrapper<Product>()
                    .eq("productStatus", true));
        } else {
            productList = productMapper.selectList(new QueryWrapper<Product>()
                    .eq("productStatus", true)
                    .eq("productType", typeId)
            );
        }
        return json.toJson(new ProductResponse<List<Product>>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, productList
        ));
    }

    @Override
    public String getProductDetails(int id) {
        Product product = productMapper.selectOne(new QueryWrapper<Product>()
                .eq("id", id)
                .eq("productStatus", true));
        return json.toJson(new ProductResponse<Product>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, product
        ));
    }

    @Override
    public String getAdminProductSuccess() {
        List<Product> products = productMapper.selectList(new LambdaQueryWrapper<Product>());
        return json.toJson(new ProductResponse<List<Product>>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, products
        ));
    }

    @Override
    public String updateProduct(Product product) {
        try {
            // 检查商品是否存在
            Product existingProduct = productMapper.selectById(product.getId());
            if (existingProduct == null) {
                return json.toJson(new ProductResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "商品不存在"
                ));
            }

            // 更新商品信息
            int result = productMapper.updateById(product);
            if (result > 0) {
                return json.toJson(new ProductResponse<String>(
                        BaseResponse.SUCCESS_CODE,
                        BaseResponse.SUCCESS_MESSAGE,
                        "更新成功"
                ));
            } else {
                return json.toJson(new ProductResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "更新失败：数据未发生变化"
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // 打印详细错误信息到控制台
            return json.toJson(new ProductResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "更新失败：" + e.getMessage()
            ));
        }
    }

    @Override
    public String deleteProduct(int id) {
        try {
            productMapper.deleteById(id);
            return json.toJson(new ProductResponse<String>(
                    BaseResponse.SUCCESS_CODE,
                    BaseResponse.SUCCESS_MESSAGE,
                    "删除成功"
            ));
        } catch (Exception e) {
            return json.toJson(new ProductResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "删除失败：" + e.getMessage()
            ));
        }
    }

    @Override
    public String addProduct(Product product) {
        try {
            // 获取最大ID
            Product maxIdProduct = productMapper.selectOne(
                new QueryWrapper<Product>()
                    .orderByDesc("id")
                    .last("limit 1")
            );
            // 设置新商品ID为最大ID + 1
            product.setId(maxIdProduct != null ? maxIdProduct.getId() + 1 : 1);
            
            // 插入新商品
            int result = productMapper.insert(product);
            if (result > 0) {
                return json.toJson(new ProductResponse<String>(
                        BaseResponse.SUCCESS_CODE,
                        BaseResponse.SUCCESS_MESSAGE,
                        "添加成功"
                ));
            } else {
                return json.toJson(new ProductResponse<String>(
                        BaseResponse.ERROR_CODE,
                        BaseResponse.ERROR_MESSAGE,
                        "添加失败：插入数据失败"
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // 打印详细错误信息到控制台
            return json.toJson(new ProductResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "添加失败：" + e.getMessage()
            ));
        }
    }
}
