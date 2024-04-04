package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.domain.QOptionSelectedProduct;
import com.nocaffeine.ssgclone.product.dto.response.OptionSelectedProductResponseDto;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_SELECTED_OPTION_PRODUCT;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OptionSelectedProductServiceImpl implements OptionSelectedProductService {

    private final OptionSelectedProductRepository optionSelectedProductRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public OptionSelectedProductResponseDto getOptionSelectedProduct(Long id) {
        OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_SELECTED_OPTION_PRODUCT));

        return OptionSelectedProductResponseDto.fromOptionSelectedProduct(optionSelectedProduct);
    }

    @Override
    public OptionSelectedProductResponseDto getOptionSelectedProductByProductIdAndOptions(Long productId, Long sizeOptionId, Long colorOptionId, Long addOptionId) {
        OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findByProductIdAndSizeOptionIdAndColorOptionIdAndAddOptionId(productId, sizeOptionId, colorOptionId, addOptionId)
                .orElseThrow(() -> new BaseException(NO_SELECTED_OPTION_PRODUCT));

        return OptionSelectedProductResponseDto.fromOptionSelectedProduct(optionSelectedProduct);
    }

    @Override
    public List<OptionSelectedProductResponseDto> getOptionSelectedProductListBySelectedOptionsList(Long productId, Long colorOptionId, Long sizeOptionId, Long addOptionId) {
        QOptionSelectedProduct qOptionSelectedProduct = QOptionSelectedProduct.optionSelectedProduct;
        // QueryDSL 을 사용하여 동적 쿼리를 작성하기 위해 QOptionSelectedProduct 를 선언한다.
        // optionSelectedProduct 는 OptionSelectedProduct 엔티티를 대표하는 별칭이다.

        // JPAQuery 는 QueryDSL 을 사용하여 JPA 쿼리를 작성하기 위한 클래스이다.
        JPAQuery<OptionSelectedProduct> query = jpaQueryFactory
                .selectFrom(qOptionSelectedProduct)
                .where(qOptionSelectedProduct.product.id
                        .eq(productId));
        // selectFrom 을 사용하여 qOptionSelectedProduct 를 조회한다.
        // where 절에 product.id 가 productId 와 일치하는 것을 찾는다.

        if (colorOptionId != null) { // colorOptionId 가 null 이 아니라면
            query.where(qOptionSelectedProduct.colorOption.id.eq(colorOptionId));
        } // where 절에 colorOption.id 가 colorOptionId 와 일치하는 것을 찾는다.
        if (sizeOptionId != null) {
            query.where(qOptionSelectedProduct.sizeOption.id.eq(sizeOptionId));
        }
        if (addOptionId != null) {
            query.where(qOptionSelectedProduct.addOption.id.eq(addOptionId));
        }

        List<OptionSelectedProduct> optionSelectedProducts = query.fetch();

        if (optionSelectedProducts.isEmpty()) {
            throw new BaseException(NO_SELECTED_OPTION_PRODUCT);
        }

        return OptionSelectedProductResponseDto.fromOptionSelectedProductList(optionSelectedProducts);
    }
}
