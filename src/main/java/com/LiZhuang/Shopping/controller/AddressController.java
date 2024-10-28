package com.LiZhuang.Shopping.controller;

import com.LiZhuang.Shopping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/addAddress/{userId}/{topic}/{address}/{username}/{sex}/{mobile}")
    public String addAddress(@PathVariable String userId,
                             @PathVariable String topic, @PathVariable String address,
                             @PathVariable String username, @PathVariable String sex,
                             @PathVariable String mobile) {
        return addressService.addAddress(userId, topic, address, username, sex, mobile);
    }

    @GetMapping("/findAll/{userId}")
    public String findAll(@PathVariable String userId) {
        return addressService.findAllAddress(userId);
    }

    @GetMapping("/findAddress/{userId}/{addressId}")
    public String findAddress(@PathVariable String userId,
                              @PathVariable String addressId) {
        return addressService.findAddress(userId, addressId);
    }

    @GetMapping("/update/{userId}/{addressId}/{topic}/{address}/{username}/{sex}/{mobile}")
    public String update(@PathVariable String userId,
                         @PathVariable int addressId,
                         @PathVariable String topic, @PathVariable String address,
                         @PathVariable String username, @PathVariable String sex,
                         @PathVariable String mobile) {
        return addressService.updateAddress(userId, topic, address, username, sex, mobile, addressId);
    }

    @GetMapping("/delete/{userId}/{addressId}")
    public String delete(@PathVariable String userId,
                         @PathVariable int addressId) {
        return addressService.deleteAddress(userId, addressId);
    }
}
