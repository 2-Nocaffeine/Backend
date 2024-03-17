package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.product.application.TotalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/total")
@Slf4j
public class TotalController {

    private final TotalService totalService;
}
