package com.nocaffeine.ssgclone.product.vo.response.viewhistory;

import com.nocaffeine.ssgclone.product.dto.response.viewhistory.ViewHistoryResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ViewHistoryResponseVo {

    private Long productId;

    public ViewHistoryResponseVo(Long productId) {
        this.productId = productId;
    }

    public static List<ViewHistoryResponseVo> viewHistoryDtoToVo(List<ViewHistoryResponseDto> getViewHistory) {

        List<ViewHistoryResponseVo> viewHistoryResponseVo = new ArrayList<>();

        for (ViewHistoryResponseDto viewHistoryResponseDto : getViewHistory) {
            viewHistoryResponseVo.add(new ViewHistoryResponseVo(
                    viewHistoryResponseDto.getProductId()
            ));
        }

        return viewHistoryResponseVo;
    }
}
