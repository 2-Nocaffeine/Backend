package com.nocaffeine.ssgclone.deliveryaddress.application;

import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressAddRequestDto;

public interface DeliveryAddressService {

    void addDeliveryAddress(DeliveryAddressAddRequestDto deliveryAddressAddRequestDto, String memberUuid);
//    void removeDeliveryAddress(DeliveryAddressRemoveRequest deliveryAddressRemoveRequest, String memberUuid);
//    void modifyDeliveryAddress(DeliveryAddressModifyRequest deliveryAddressModifyRequest, String memberUuid);
//    List<DeliveryAddressListResponse> findDeliveryAddress(String memberUuid);
//    void setDefaultDeliveryAddress(DeliveryAddressSetDefaultRequest deliveryAddressSetDefaultRequest, String memberUuid);


}
