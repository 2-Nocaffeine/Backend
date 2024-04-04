package com.nocaffeine.ssgclone.order.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.order.application.OrderService;
import com.nocaffeine.ssgclone.order.dto.request.GuestOrderInfoRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.OrderIdRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.OrderNumberRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.UserOrderSaveRequestDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderInfoAndProductListResponseDto;
import com.nocaffeine.ssgclone.order.vo.request.GuestOrderInfoRequestVo;
import com.nocaffeine.ssgclone.order.vo.request.OrderIdRequestVo;
import com.nocaffeine.ssgclone.order.vo.request.UserOrderProductRequestVo;
import com.nocaffeine.ssgclone.order.vo.response.OrderIdListResponseVo;
import com.nocaffeine.ssgclone.order.vo.response.MemberOrderInfoResponseVo;
import com.nocaffeine.ssgclone.order.vo.response.OrderInfoAndProductListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
;import java.util.List;

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

        String memberUuid = "guest";
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
    @Operation(summary = "회원 주문 id 호출", description = "회원 주문 id 호출", tags = {"Order"})
    @GetMapping("/member-order-id-list")
    public CommonResponse<List<OrderIdListResponseVo>> orderIdList(){

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        return CommonResponse.success("주문 목록을 불러왔습니다.", OrderIdListResponseVo.convertToVo(orderService.findOrderIdList(memberUuid)));
    }

    //회원 주문 상품 조회
    @Operation(summary = "주문 상품 및 주문 정보 조회", description = "주문 상품 및 주문 정보 조회", tags = {"Order"})
    @GetMapping("/{orderId}/member-order-product/")
    public CommonResponse<OrderInfoAndProductListResponseVo> orderProductList(@PathVariable Long orderId){

        OrderNumberRequestDto orderNumberRequestDto = new OrderNumberRequestDto(orderId);
        OrderInfoAndProductListResponseDto orderInfoAndProductListResponseDto = orderService.findOrderProductList(orderNumberRequestDto);


        return CommonResponse.success("주문 상품을 불러왔습니다.", OrderInfoAndProductListResponseVo.convertToVo(orderInfoAndProductListResponseDto));
    }

    @Operation(summary = "비주문 주문 조회", description = "비주문 주문 조회", tags = {"Order"})
    @GetMapping("/guest/order")
    public CommonResponse<OrderInfoAndProductListResponseVo> guestOrderList(@RequestParam String orderName,
                                                                     @RequestParam String phoneNumber,
                                                                     @RequestParam String orderNumber, GuestOrderInfoRequestVo guestOrderInfoRequestVo) {

        GuestOrderInfoRequestDto guestOrderInfoRequestDto = GuestOrderInfoRequestDto.convertToDto(guestOrderInfoRequestVo);

        return CommonResponse.success("비회원 주문 정보를 불러왔습니다.",OrderInfoAndProductListResponseVo
                .convertToVo(orderService.findGuestOrderInfo(guestOrderInfoRequestDto)) );
    }


}