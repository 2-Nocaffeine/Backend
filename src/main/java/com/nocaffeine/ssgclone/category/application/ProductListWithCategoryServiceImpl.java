package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.dto.response.ProductListIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductListWithCategoryServiceImpl implements ProductListWithCategoryService {

    private final ProductCategoryListRepository productCategoryListRepository;

    @Override
    public List<ProductListIdResponse> getProductListWithCategory(long largeId) {
        List<ProductListIdResponse> productListIdResponseList = new ArrayList<>();
        int sequenceId = 1;

        for (ProductCategoryList productCategoryList : productCategoryListRepository.findByLargeCategory_Id(largeId)){
            ProductListIdResponse productListIdResponse = ProductListIdResponse.builder()
                    .id(sequenceId++)
                    .ProductId(productCategoryList.getProduct().getId())
                    .build();
            productListIdResponseList.add(productListIdResponse);
        }
        return productListIdResponseList;
    }
}
