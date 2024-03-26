package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ViewHistoryResponseVo {

    private final Long id;
    private final Long productId;

    public ViewHistoryResponseVo(Long id, Long productId) {
        this.id = id;
        this.productId = productId;
    }

    public static List<ViewHistoryResponseVo> viewHistoryDtoToVo(List<ViewHistoryDto> getViewHistory) {

        List<ViewHistoryResponseVo> viewHistoryResponseVo = new ArrayList<>();

        for (ViewHistoryDto viewHistoryDto : getViewHistory) {
            viewHistoryResponseVo.add(new ViewHistoryResponseVo(
                    viewHistoryDto.getId(),
                    viewHistoryDto.getProductId()
            ));
        }

        return viewHistoryResponseVo;
    }
}
