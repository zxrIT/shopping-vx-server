package com.ZengXiangRui.Shopping.service.impl;

import com.ZengXiangRui.Shopping.entity.database.Address;
import com.ZengXiangRui.Shopping.entity.response.BaseResponse;
import com.ZengXiangRui.Shopping.entity.response.address.AddressResponse;
import com.ZengXiangRui.Shopping.mapper.AddressMapper;
import com.ZengXiangRui.Shopping.service.AddressService;
import com.ZengXiangRui.Shopping.util.json.Json;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private Json json;

    @Override
    public String addAddress(String userId, String topic, String address, String username,
                             String sex, String mobile) {
        List<Address> addressList = addressMapper.selectList(new QueryWrapper<Address>().eq("id", userId));
        Address addressEntity = new Address();
        if (addressList.size() == 0) {
            addressEntity.setAddressId(1);
        } else if (addressList.size() >= 1 && addressList.size() < 4) {
            for (Address addressObject : addressList) {
                int maxAddressId = 0;
                if (addressObject.getAddressId() > maxAddressId) {
                    maxAddressId = addressObject.getAddressId();
                }
                addressEntity.setAddressId(maxAddressId + 1);
            }
        } else if (addressList.size() >= 4) {
            return json.toJson(new AddressResponse<String>(BaseResponse.ERROR_CODE,
                    BaseResponse.ERROR_MESSAGE, "您最多添加4个地址"));
        }
        addressEntity.setId(userId);
        addressEntity.setTopic(topic);
        addressEntity.setUsername(username);
        addressEntity.setAddressContent(address);
        addressEntity.setSex(sex);
        addressEntity.setMobile(mobile);
        addressMapper.insert(addressEntity);
        return Json.toJson(new AddressResponse<String>(BaseResponse.SUCCESS_CODE,
                BaseResponse.SUCCESS_MESSAGE, "添加成功"));
    }

    @Override
    public String findAllAddress(String userId) {
        List<Address> addressList = addressMapper.selectList(new QueryWrapper<Address>()
                .eq("id", userId));
        return Json.toJson(new AddressResponse<List<Address>>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, addressList
        ));
    }

    @Override
    public String findAddress(String userId, String addressId) {
        Address address = addressMapper.selectOne(new QueryWrapper<Address>().eq("id", userId).eq("addressId", addressId));
        return Json.toJson(new AddressResponse<Address>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, address
        ));
    }

    @Override
    public String updateAddress(String userId, String topic, String address,
                                String username, String sex, String mobile, int addressId) {
        Address addressEntity = new Address();
        addressEntity.setAddressId(addressId);
        addressEntity.setId(userId);
        addressEntity.setTopic(topic);
        addressEntity.setAddressContent(address);
        addressEntity.setUsername(username);
        addressEntity.setSex(sex);
        addressEntity.setMobile(mobile);
        try {
            addressMapper.update(addressEntity, new QueryWrapper<Address>().eq("id", userId)
                    .eq("addressId", addressId));
        } catch (Exception e) {
            return Json.toJson(new AddressResponse<String>(
                    BaseResponse.ERROR_CODE, BaseResponse.ERROR_MESSAGE, "修改失败"
            ));
        }
        return Json.toJson(new AddressResponse<String>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, "修改成功"
        ));
    }

    @Override
    public String deleteAddress(String userId, int addressId) {
        try {
            addressMapper.delete(new QueryWrapper<Address>().eq("id", userId).eq("addressId", addressId));
        } catch (Exception e) {
            return Json.toJson(new AddressResponse<String>(
                    BaseResponse.ERROR_CODE, BaseResponse.ERROR_MESSAGE, "删除失败"
            ));
        }
        return Json.toJson(new AddressResponse<String>(
                BaseResponse.SUCCESS_CODE, BaseResponse.SUCCESS_MESSAGE, "删除成功"
        ));
    }
}
