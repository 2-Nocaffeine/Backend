package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.domain.ProductImage;
import com.nocaffeine.ssgclone.product.dto.response.productimage.ProductImageResponseDto;
import com.nocaffeine.ssgclone.product.infrastructure.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImagesServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Override
    public List<ProductImageResponseDto> getProductImageList(Long id) {
        List<ProductImage> productImages = productImageRepository.findByProductId(id);

        List<ProductImageResponseDto> productImageList = new ArrayList<>();

        for (ProductImage productImage : productImages) {
            productImageList.add(ProductImageResponseDto.fromProductImage(productImage));
        }

        return productImageList;
    }

}
