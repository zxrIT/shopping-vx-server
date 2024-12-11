package com.ZengXiangRui.Shopping.service;

import com.ZengXiangRui.Shopping.entity.database.Address;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AddressService extends IService<Address> {
    String addAddress(String userId, String topic, String address,
                      String username, String sex, String mobile);

    String findAllAddress(String userId);

    String findAddress(String userId, String addressId);

    String updateAddress(String userId, String topic, String address,
                         String username, String sex, String mobile, int addressId);

    String deleteAddress(String userId, int addressId);
}
