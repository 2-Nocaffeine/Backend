package com.nocaffeine.ssgclone.order.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.order.application.OrderService;
import com.nocaffeine.ssgclone.order.dto.UserOrderSaveDto;
import com.nocaffeine.ssgclone.order.vo.request.UserOrderProductRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final JwtTokenProvider jwtTokenProvider;

    //회원 주문
    //주문상품id랑 총 개수 받아와야함 리스트로
    @PostMapping("/member")
    public CommonResponse<String> memberOrderAdd(@RequestBody UserOrderProductRequestVo userOrderProductRequestVo) {

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        //vo를 dto로 변환
        UserOrderSaveDto userOrderSaveDto = UserOrderSaveDto.convertToDto(memberUuid, userOrderProductRequestVo);

        orderService.addMemberOrder(userOrderSaveDto);

        return CommonResponse.success("주문이 완료되었습니다.");

    }

    //비회원 주문
    @PostMapping("/guest")
    public CommonResponse<String> nonMemberOrderAdd(@RequestBody UserOrderProductRequestVo userOrderProductRequestVo) {

        String memberUuid = "guest";
        UserOrderSaveDto userOrderProduct = UserOrderSaveDto.convertToDto(memberUuid,userOrderProductRequestVo);

        orderService.addMemberOrder(userOrderProduct);

        return CommonResponse.success("비회원 주문이 완료되었습니다.");
    }


}