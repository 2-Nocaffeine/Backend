package com.nocaffeine.ssgclone.product.vo.response.productimage;

import com.nocaffeine.ssgclone.product.domain.Image;
import com.nocaffeine.ssgclone.product.dto.response.productimage.ProductImageResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductThumbnailResponseVo {

    private Long imageId;
    private String url;

    public ProductThumbnailResponseVo(Image image) {
        this.imageId = image.getId();
        this.url = image.getUrl();
    }

    public static ProductThumbnailResponseVo productImageDtoToVo(List<ProductImageResponseDto> productImageResponseDto) {
        return new ProductThumbnailResponseVo(
                productImageResponseDto.get(0).getImage()
        );
    }
}
