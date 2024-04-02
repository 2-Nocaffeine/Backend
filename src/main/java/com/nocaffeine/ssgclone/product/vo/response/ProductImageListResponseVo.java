package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.response.ProductImageResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductImageListResponseVo {

    private Long imageId;
    private String url;

    public ProductImageListResponseVo(Long imageId, String url) {
        this.imageId = imageId;
        this.url = url;
    }

    public static List<ProductImageListResponseVo> productImageDtoToVo(List<ProductImageResponseDto> getProductImageResponseDto) {

        List<ProductImageListResponseVo> getProductImageListResponseVo = new ArrayList<>();

        for (ProductImageResponseDto productImageResponseDto : getProductImageResponseDto) {
            getProductImageListResponseVo.add(new ProductImageListResponseVo(
                    productImageResponseDto.getImage().getId(),
                    productImageResponseDto.getImage().getUrl()
            ));
        }

        return getProductImageListResponseVo;
    }
}
