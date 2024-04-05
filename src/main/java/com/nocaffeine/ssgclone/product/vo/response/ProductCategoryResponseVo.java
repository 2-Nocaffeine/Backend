package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.response.ProductCategoryResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCategoryResponseVo {

    private Long largeCategoryId;
    private String largeCategoryName;
    private Long mediumCategoryId;
    private String mediumCategoryName;
    private Long smallCategoryId;
    private String smallCategoryName;

    public ProductCategoryResponseVo(Long largeCategoryId, String largeCategoryName, Long mediumCategoryId, String mediumCategoryName, Long smallCategoryId, String smallCategoryName) {
        this.largeCategoryId = largeCategoryId;
        this.largeCategoryName = largeCategoryName;
        this.mediumCategoryId = mediumCategoryId;
        this.mediumCategoryName = mediumCategoryName;
        this.smallCategoryId = smallCategoryId;
        this.smallCategoryName = smallCategoryName;
    }

    public static ProductCategoryResponseVo productCategoryDtoToVo(ProductCategoryResponseDto productCategoryResponseDto) {
        return new ProductCategoryResponseVo(
                productCategoryResponseDto.getLargeCategory().getId(),
                productCategoryResponseDto.getLargeCategory().getName(),
                productCategoryResponseDto.getMediumCategory().getId(),
                productCategoryResponseDto.getMediumCategory().getName(),
                productCategoryResponseDto.getSmallCategory().getId(),
                productCategoryResponseDto.getSmallCategory().getName()
        );
    }
}
