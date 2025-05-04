package com.ZengXiangRui.Shopping.controller;

import com.ZengXiangRui.Shopping.entity.database.Product;
import com.ZengXiangRui.Shopping.entity.response.BaseResponse;
import com.ZengXiangRui.Shopping.entity.response.product.ProductResponse;
import com.ZengXiangRui.Shopping.mapper.ProductMapper;
import com.ZengXiangRui.Shopping.util.json.Json;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Autowired
    private Json json;

    @Autowired
    private ProductMapper productMapper;

    @PostMapping("/product/image")
    public String uploadProductImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return json.toJson(new ProductResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "文件为空"
            ));
        }

        try {
            // 确保目录存在
            File uploadDir = new File(uploadPath + "/product");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 获取商品表中的最大ID
            Product maxIdProduct = productMapper.selectOne(
                new QueryWrapper<Product>()
                    .orderByDesc("id")
                    .last("limit 1")
            );
            int nextId = (maxIdProduct != null ? maxIdProduct.getId() : 0) + 1;
            
            // 使用nextId作为文件名
            String imageName = String.valueOf(nextId);
            
            // 保存文件
            File destFile = new File(uploadDir.getAbsolutePath() + File.separator + imageName + ".png");
            file.transferTo(destFile);

            // 返回文件名（不带扩展名）
            Map<String, String> result = new HashMap<>();
            result.put("imageName", imageName);
            
            return json.toJson(new ProductResponse<Map<String, String>>(
                    BaseResponse.SUCCESS_CODE,
                    BaseResponse.SUCCESS_MESSAGE,
                    result
            ));
        } catch (IOException e) {
            e.printStackTrace(); // 添加错误日志打印
            return json.toJson(new ProductResponse<String>(
                    BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE,
                    "文件上传失败：" + e.getMessage()
            ));
        }
    }
} 