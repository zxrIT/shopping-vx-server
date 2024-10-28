package com.LiZhuang.Shopping.controller;

import com.LiZhuang.Shopping.entity.database.Collect;
import com.LiZhuang.Shopping.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @GetMapping("/add/{userId}/{id}")
    public String addCollect(@PathVariable String userId,
                             @PathVariable int id) {
        return collectService.addCollect(userId, id);
    }

    @GetMapping("/all/{userId}")
    public String allCollect(@PathVariable String userId) {
        return collectService.allCollect(userId);
    }

    @GetMapping("/delete/{userId}/{id}")
    public String deleteCollect(@PathVariable String userId,
                                @PathVariable int id) {
        return collectService.deleteCollect(userId, id);
    }
}
