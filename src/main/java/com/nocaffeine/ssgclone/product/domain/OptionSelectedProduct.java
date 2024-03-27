package com.nocaffeine.ssgclone.product.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.OUT_OF_STOCK_PRODUCT;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public OptionSelectedProduct(Product product, SizeOption sizeOption, ColorOption colorOption, AddOption addOption, int stock) {
        this.product = product;
        this.sizeOption = sizeOption;
        this.colorOption = colorOption;
        this.addOption = addOption;
        this.stock = stock;
    }

    public void decreaseStock(int quantity) {
        int restStock = this.stock - quantity;
        if (restStock < 0) {
            throw new BaseException(OUT_OF_STOCK_PRODUCT);
        }
        this.stock = restStock;
    }
}
