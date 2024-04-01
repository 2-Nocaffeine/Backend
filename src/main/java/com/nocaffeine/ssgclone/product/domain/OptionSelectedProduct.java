package com.nocaffeine.ssgclone.product.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.dto.OrderedProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.OUT_OF_STOCK_PRODUCT;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionSelectedProduct extends BaseTimeEntity { // BaseTimeEntity를 상속받음으로써 생성시간, 수정시간을 자동으로 관리한다.

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private SizeOption sizeOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private ColorOption colorOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private AddOption addOption;

    @NotNull
    private int stock;

}
