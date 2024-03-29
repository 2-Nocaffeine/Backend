package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ViewHistoryResponseVo {

    private final Long productId;

    public ViewHistoryResponseVo(Long productId) {
        this.productId = productId;
    }

    public static List<ViewHistoryResponseVo> viewHistoryDtoToVo(List<ViewHistoryDto> getViewHistory) {

        List<ViewHistoryResponseVo> viewHistoryResponseVo = new ArrayList<>();

        for (ViewHistoryDto viewHistoryDto : getViewHistory) {
            viewHistoryResponseVo.add(new ViewHistoryResponseVo(
                    viewHistoryDto.getProductId()
            ));
        }

        return viewHistoryResponseVo;
    }
}
