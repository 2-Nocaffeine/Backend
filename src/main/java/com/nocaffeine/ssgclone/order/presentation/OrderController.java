package com.nocaffeine.ssgclone.order.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.order.application.OrderService;
import com.nocaffeine.ssgclone.order.dto.MemberOrderSaveDto;
import com.nocaffeine.ssgclone.order.vo.request.MemberOrderSaveRequestVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final JwtTokenProvider jwtTokenProvider;

    //회원 주문
    //주문상품 받아와야함 리스트로
    @PostMapping("/member")
    public CommonResponse<String> memberOrderAdd(@RequestBody @Valid MemberOrderSaveRequestVo memberOrderSaveRequestVo) {
        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        orderService.addMemberOrder(MemberOrderSaveDto.convertToDto(memberUuid,memberOrderSaveRequestVo));

        return CommonResponse.success("주문이 완료되었습니다.");

    }


}