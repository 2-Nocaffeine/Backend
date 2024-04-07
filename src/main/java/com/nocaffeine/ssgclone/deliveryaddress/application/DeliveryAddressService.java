package com.nocaffeine.ssgclone.deliveryaddress.application;

import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressAddRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressModifyRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressSetDefaultRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.response.DeliveryAddressListResponseDto;

import java.util.List;

public interface DeliveryAddressService {

    void addDeliveryAddress(DeliveryAddressAddRequestDto deliveryAddressAddRequestDto, String memberUuid);
    void removeDeliveryAddress(Long addressId , String memberUuid);
    void modifyDeliveryAddress(DeliveryAddressModifyRequestDto deliveryAddressModifyRequestDto);
    List<DeliveryAddressListResponseDto> findDeliveryAddress(String memberUuid);
    void setDefaultDeliveryAddress(DeliveryAddressSetDefaultRequestDto deliveryAddressSetDefaultRequestDto, String memberUuid);


}
