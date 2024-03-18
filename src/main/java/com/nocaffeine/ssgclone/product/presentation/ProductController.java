package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.product.dto.ProductResponseDto;
import com.nocaffeine.ssgclone.product.application.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseDto<ProductResponseDto> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
        // @PathVariable 은 URL 경로에 변수를 넣어주는 것이다.
        // 작동원리는 @PathVariable 을 통해 id를 받아온다.
        // 그리고 productService.getProduct(id) 를 통해 id로 조회한 객체를 리턴한다.
        // 이때, id가 없으면 null 이 리턴된다.
        // 이러한 동작은 productService.getProduct(id) 메소드에서 orElse(null) 이기 때문이다.
    }


}
