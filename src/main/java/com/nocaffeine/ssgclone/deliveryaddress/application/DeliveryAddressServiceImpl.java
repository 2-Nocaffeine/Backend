package com.nocaffeine.ssgclone.deliveryaddress.application;


import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.deliveryaddress.domain.DeliveryAddress;
import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressAddRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.response.DeliveryAddressListResponseDto;
import com.nocaffeine.ssgclone.deliveryaddress.infrastructure.DeliveryAddressRepository;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXIST_ADDRESS;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXIST_MEMBERS;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class DeliveryAddressServiceImpl implements DeliveryAddressService{

    private final MemberRepository memberRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;

    @Override
    @Transactional
    public void addDeliveryAddress(DeliveryAddressAddRequestDto deliveryAddressAddRequestDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid).orElseThrow(() ->
                new BaseException(NO_EXIST_MEMBERS));

        deliveryAddressRepository.save(DeliveryAddress.builder()
                .member(member)
                .addressName(deliveryAddressAddRequestDto.getAddressName())
                .recipient(deliveryAddressAddRequestDto.getRecipient())
                .phoneNumber(deliveryAddressAddRequestDto.getPhoneNumber())
                .zip(deliveryAddressAddRequestDto.getZip())
                .post(deliveryAddressAddRequestDto.getPost())
                .street(deliveryAddressAddRequestDto.getStreet())
                .defaultCheck(false)
                .build());
    }


    @Override
    @Transactional
    public void removeDeliveryAddress(Long addressId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid).orElseThrow(() ->
                new BaseException(NO_EXIST_MEMBERS));

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(addressId).orElseThrow(() ->
                new BaseException(NO_EXIST_ADDRESS));

        deliveryAddressRepository.delete(deliveryAddress);
    }


    @Override
    public List<DeliveryAddressListResponseDto> findDeliveryAddress(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid).orElseThrow(() ->
                new BaseException(NO_EXIST_MEMBERS));

        List<DeliveryAddress> deliveryAddresses = deliveryAddressRepository.findByMember(member);

        List<DeliveryAddressListResponseDto> deliveryAddressListResponseDto = new ArrayList<>();
        for (DeliveryAddress deliveryAddress : deliveryAddresses) {
            deliveryAddressListResponseDto.add(DeliveryAddressListResponseDto.builder()
                    .deliveryAddressId(deliveryAddress.getId())
                    .addressName(deliveryAddress.getAddressName())
                    .recipient(deliveryAddress.getRecipient())
                    .phoneNumber(deliveryAddress.getPhoneNumber())
                    .zip(deliveryAddress.getZip())
                    .post(deliveryAddress.getPost())
                    .street(deliveryAddress.getStreet())
                    .defaultCheck(deliveryAddress.isDefaultCheck())
                    .build());

        }

        return deliveryAddressListResponseDto;
    }
}
