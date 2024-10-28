package com.LiZhuang.Shopping.service.impl;

import com.LiZhuang.Shopping.entity.database.Collect;
import com.LiZhuang.Shopping.entity.response.BaseResponse;
import com.LiZhuang.Shopping.entity.response.collect.CollectResponse;
import com.LiZhuang.Shopping.mapper.CollectMapper;
import com.LiZhuang.Shopping.service.CollectService;
import com.LiZhuang.Shopping.util.json.Json;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@SuppressWarnings("all")
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
        implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private Json json;

    @Override
    public String addCollect(String userId, int id) {
        Collect collectObject = collectMapper.selectOne(
                new QueryWrapper<Collect>()
                        .eq("id", userId));
        if (collectObject.getProduct1() == 0) {
            collectObject.setProduct1(id);
        } else if (collectObject.getProduct2() == 0) {
            collectObject.setProduct2(id);
        } else if (collectObject.getProduct3() == 0) {
            collectObject.setProduct3(id);
        } else if (collectObject.getProduct4() == 0) {
            collectObject.setProduct4(id);
        } else {
            return json.toJson(new CollectResponse<String>(
                    BaseResponse.ERROR_CODE, BaseResponse.ERROR_MESSAGE,
                    "最多收藏4件商品"
            ));
        }
        collectMapper.updateById(collectObject);
        return json.toJson(new CollectResponse<String>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE,
                "收藏成功"
        ));
    }

    @Override
    public String allCollect(String userId) {
        Collect collect = collectMapper.selectOne(new QueryWrapper<Collect>()
                .eq("id", userId));
        ArrayList<Integer> arrayListCollect = new ArrayList<>();
        arrayListCollect.add(collect.getProduct1());
        arrayListCollect.add(collect.getProduct2());
        arrayListCollect.add(collect.getProduct3());
        arrayListCollect.add(collect.getProduct4());
        return json.toJson(new CollectResponse<ArrayList<Integer>>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE,
                arrayListCollect
        ));
    }

    @Override
    public String deleteCollect(String userId, int id) {
        Collect collect = collectMapper.selectOne(new QueryWrapper<Collect>()
                .eq("id", userId));
        if (collect.getProduct1() == id) {
            collect.setProduct1(0);
        } else if (collect.getProduct2() == id) {
            collect.setProduct2(0);
        } else if (collect.getProduct3() == id) {
            collect.setProduct3(0);
        } else if (collect.getProduct4() == id) {
            collect.setProduct4(0);
        }
        collectMapper.updateById(collect);
        return json.toJson(new CollectResponse<String>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE
                , "取消成功"
        ));
    }
}
