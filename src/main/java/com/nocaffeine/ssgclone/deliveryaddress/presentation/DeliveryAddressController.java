package com.nocaffeine.ssgclone.deliveryaddress.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.deliveryaddress.application.DeliveryAddressService;
import com.nocaffeine.ssgclone.deliveryaddress.dto.request.DeliveryAddressAddRequestDto;
import com.nocaffeine.ssgclone.deliveryaddress.vo.request.DeliveryAddressAddRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
}
