package com.LiZhuang.Shopping.service;

import com.LiZhuang.Shopping.entity.database.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CollectService extends IService<Collect> {
    String addCollect(String userId, int id);
    String allCollect(String userId);
    String deleteCollect(String userId, int id);
}
