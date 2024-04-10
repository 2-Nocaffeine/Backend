package com.nocaffeine.ssgclone.deliveryaddress.application;


import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.deliveryaddress.domain.DeliveryAddress;
import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressAddRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressModifyRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.response.DeliveryAddressDetailResponseDto;
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

    /**
     * 배송지 추가
     */
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


    /**
     * 배송지 삭제
     */
    @Override
    @Transactional
    public void removeDeliveryAddress(Long deliveryAddressId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid).orElseThrow(() ->
                new BaseException(NO_EXIST_MEMBERS));

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByIdAndMember(deliveryAddressId, member).orElseThrow(() ->
                new BaseException(NO_EXIST_ADDRESS));

        deliveryAddressRepository.delete(deliveryAddress);
    }


    /**
     * 배송지 수정
     */
    @Override
    @Transactional
    public void modifyDeliveryAddress(DeliveryAddressModifyRequestDto deliveryAddressModifyRequestDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid).orElseThrow(() ->
                new BaseException(NO_EXIST_MEMBERS));

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByIdAndMember(deliveryAddressModifyRequestDto.getDeliveryAddressId(), member).orElseThrow(() ->
                new BaseException(NO_EXIST_ADDRESS));

        deliveryAddressRepository.save(
                DeliveryAddress.builder()
                .id(deliveryAddressModifyRequestDto.getDeliveryAddressId())
                .member(deliveryAddress.getMember())
                .addressName(deliveryAddressModifyRequestDto.getAddressName())
                .recipient(deliveryAddressModifyRequestDto.getRecipient())
                .phoneNumber(deliveryAddressModifyRequestDto.getPhoneNumber())
                .zip(deliveryAddressModifyRequestDto.getZip())
                .post(deliveryAddressModifyRequestDto.getPost())
                .street(deliveryAddressModifyRequestDto.getStreet())
                .defaultCheck(deliveryAddress.isDefaultCheck())
                .build()
        );


    }


    /**
     * 배송지 조회
     */
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

    /**
     * 기본 배송지 설정
     */
    @Override
    @Transactional
    public void setDefaultDeliveryAddress(Long deliveryAddressId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid).orElseThrow(() ->
                new BaseException(NO_EXIST_MEMBERS));

        // 기존에 있던 기본 배송지 해제
        deliveryAddressRepository.findByMemberAndDefaultCheck(member, true).ifPresent(deliveryAddress -> {
            deliveryAddressRepository.save(
                    DeliveryAddress.builder()
                    .id(deliveryAddress.getId())
                    .member(deliveryAddress.getMember())
                    .addressName(deliveryAddress.getAddressName())
                    .recipient(deliveryAddress.getRecipient())
                    .phoneNumber(deliveryAddress.getPhoneNumber())
                    .zip(deliveryAddress.getZip())
                    .post(deliveryAddress.getPost())
                    .street(deliveryAddress.getStreet())
                    .defaultCheck(false)
                    .build()
            );
        });

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findById(deliveryAddressId).orElseThrow(() ->
                new BaseException(NO_EXIST_ADDRESS));

        deliveryAddressRepository.save(
                DeliveryAddress.builder()
                .id(deliveryAddress.getId())
                .member(deliveryAddress.getMember())
                .addressName(deliveryAddress.getAddressName())
                .recipient(deliveryAddress.getRecipient())
                .phoneNumber(deliveryAddress.getPhoneNumber())
                .zip(deliveryAddress.getZip())
                .post(deliveryAddress.getPost())
                .street(deliveryAddress.getStreet())
                .defaultCheck(true)
                .build()
        );
    }

    /**
     * 배송지 상세 조회
     */
    @Override
    public DeliveryAddressDetailResponseDto findDeliveryAddressDetail(Long addressId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid).orElseThrow(() ->
                new BaseException(NO_EXIST_MEMBERS));

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByIdAndMember(addressId, member).orElseThrow(() ->
                new BaseException(NO_EXIST_ADDRESS));

        return DeliveryAddressDetailResponseDto.builder()
                .deliveryAddressId(deliveryAddress.getId())
                .addressName(deliveryAddress.getAddressName())
                .recipient(deliveryAddress.getRecipient())
                .phoneNumber(deliveryAddress.getPhoneNumber())
                .zip(deliveryAddress.getZip())
                .post(deliveryAddress.getPost())
                .street(deliveryAddress.getStreet())
                .defaultCheck(deliveryAddress.isDefaultCheck())
                .build();
    }


}
