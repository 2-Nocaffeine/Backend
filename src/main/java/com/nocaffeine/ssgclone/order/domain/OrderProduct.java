package com.nocaffeine.ssgclone.order.domain;

import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private OptionSelectedProduct optionSelectedProduct;

    @NotNull
    private int price;

    @NotNull
    private int quantity;


}
