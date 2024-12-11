package com.ZengXiangRui.Shopping.service;

import com.ZengXiangRui.Shopping.entity.database.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CollectService extends IService<Collect> {
    String addCollect(String userId, int id);
    String allCollect(String userId);
    String deleteCollect(String userId, int id);
    String getAllCollect(String userId);
}
