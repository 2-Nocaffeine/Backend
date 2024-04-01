package com.nocaffeine.ssgclone.order.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.order.application.OrderService;
import com.nocaffeine.ssgclone.order.dto.request.OrderIdRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.UserOrderSaveRequestDto;
import com.nocaffeine.ssgclone.order.vo.request.OrderIdRequestVo;
import com.nocaffeine.ssgclone.order.vo.request.UserOrderProductRequestVo;
import com.nocaffeine.ssgclone.order.vo.response.OrderIdListResponseVo;
import com.nocaffeine.ssgclone.order.vo.response.OrderListResponseVo;
import com.nocaffeine.ssgclone.order.vo.response.MemberOrderInfoResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final JwtTokenProvider jwtTokenProvider;

    //회원 주문
    @Operation(summary = "회원 주문", description = "회원 주문", tags = {"Order"})
    @PostMapping("/member")
    public CommonResponse<String> memberOrderAdd(@RequestBody UserOrderProductRequestVo userOrderProductRequestVo) {

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        //vo를 dto로 변환
        UserOrderSaveRequestDto userOrderSaveRequestDto = UserOrderSaveRequestDto.convertToDto(memberUuid, userOrderProductRequestVo);

        orderService.addMemberOrder(userOrderSaveRequestDto);

        return CommonResponse.success("주문이 완료되었습니다.");

    }

    //비회원 주문
    @Operation(summary = "비회원 주문", description = "비회원 주문", tags = {"Order"})
    @PostMapping("/guest")
    public CommonResponse<String> nonMemberOrderAdd(@RequestBody UserOrderProductRequestVo userOrderProductRequestVo) {

        String memberUuid = "guest"; //중복체크하면 안들어가가ㅔㅅ네...
        UserOrderSaveRequestDto userOrderProduct = UserOrderSaveRequestDto.convertToDto(memberUuid,userOrderProductRequestVo);

        orderService.addMemberOrder(userOrderProduct);

        return CommonResponse.success("비회원 주문이 완료되었습니다.");
    }

    // 주문 취소
    @Operation(summary = "주문 취소", description = "주문 취소", tags = {"Order"})
    @DeleteMapping
    public CommonResponse<String> orderRemove(@RequestBody OrderIdRequestVo orderIdRequestVo) {

        OrderIdRequestDto orderIdRequestDto = OrderIdRequestDto.convertToDto(orderIdRequestVo);
        orderService.removeOrder(orderIdRequestDto);

        return CommonResponse.success("주문이 취소되었습니다.");

    }

    // 주문자 정보 조회
    @Operation(summary = "주문자 정보 조회", description = "주문자 정보 조회", tags = {"Order"})
    @GetMapping("/member-info")
    public CommonResponse<MemberOrderInfoResponseVo> orderMemberList(){

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        return CommonResponse.success("주문자 정보를 불러왔습니다.", MemberOrderInfoResponseVo.convertToVo(orderService.findOrderInfo(memberUuid)));

    }

    //회원 주문 id 호출
    @Operation(summary = "회원 주문id 호출", description = "회원 주문id 호출", tags = {"Order"})
    @GetMapping("/member-order-list")
    public CommonResponse<OrderIdListResponseVo> orderIdList(){

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        return CommonResponse.success("주문 목록을 불러왔습니다.", OrderIdListResponseVo.convertToVo(orderService.findOrderIdList(memberUuid)));
    }

}