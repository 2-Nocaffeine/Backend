package com.nocaffeine.ssgclone.order.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.order.application.OrderService;
import com.nocaffeine.ssgclone.order.dto.request.OrderSaveRequest;
import com.nocaffeine.ssgclone.order.dto.response.OrderSaveResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
<<<<<<< HEAD

    private final OrderService orderService;

    @PostMapping("/order")
    public CommonResponse<String> orderSave(@RequestBody @Valid OrderSaveRequest orderRequest){
        OrderService.saveOrder(orderRequest);
        return CommonResponse.success("주문 성공");
    }
=======
>>>>>>> f15de94571eb876551d56beddc477e9738ba2c89
}
