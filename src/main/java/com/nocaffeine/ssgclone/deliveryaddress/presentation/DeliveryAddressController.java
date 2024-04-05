package com.nocaffeine.ssgclone.deliveryaddress.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.deliveryaddress.application.DeliveryAddressService;
import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressAddRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.dto.response.DeliveryAddressListResponseDto;
import com.nocaffeine.ssgclone.deliveryaddress.vo.request.DeliveryAddressAddRequestVo;
import com.nocaffeine.ssgclone.deliveryaddress.vo.response.DeliveryAddressListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Member")
@RequestMapping("/api/v1/member/delivery-address")
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "새 배송지 추가", description = "새 배송지 추가")
    @PostMapping
    public CommonResponse<String> addDeliveryAddress(@RequestBody DeliveryAddressAddRequestVo deliveryAddressAddRequestVo) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        deliveryAddressService.addDeliveryAddress(DeliveryAddressAddRequestDto.voToDto(deliveryAddressAddRequestVo), memberUuid);
        return CommonResponse.success("배송지 추가 성공");
    }

    @Operation(summary = "배송지 삭제", description = "배송지 삭제")
    @DeleteMapping("{addressId}")
    public CommonResponse<String> removeDeliveryAddress(@PathVariable("addressId") Long addressId) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        deliveryAddressService.removeDeliveryAddress(addressId, memberUuid);
        return CommonResponse.success("배송지 삭제 성공");
    }

    @Operation(summary = "배송지 리스트 조회", description = "배송지 리스트 조회")
    @GetMapping
    public CommonResponse<List<DeliveryAddressListResponseVo>> findDeliveryAddress() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        List<DeliveryAddressListResponseVo> deliveryAddressListResponseVo = new ArrayList<>();
        for(DeliveryAddressListResponseDto address : deliveryAddressService.findDeliveryAddress(memberUuid)) {
            deliveryAddressListResponseVo.add(DeliveryAddressListResponseDto.dtoToVo(address));
        }

        return CommonResponse.success("배송지 조회 성공", deliveryAddressListResponseVo);
    }

}
