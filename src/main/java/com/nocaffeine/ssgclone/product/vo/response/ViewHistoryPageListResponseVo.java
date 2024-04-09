package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.response.ViewHistoryPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.ViewHistoryResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ViewHistoryPageListResponseVo {

    private boolean next;
    private boolean last;
    private List<ViewHistoryResponseVo>  viewHistoryProductList;

    public ViewHistoryPageListResponseVo(boolean next, boolean last, List<ViewHistoryResponseDto> viewHistoryProductList) {
        this.next = next;
        this.last = last;
        this.viewHistoryProductList = ViewHistoryResponseVo.viewHistoryDtoToVo(viewHistoryProductList);
    }

    public static ViewHistoryPageListResponseVo fromViewHistoryPageListResponseDto(ViewHistoryPageListResponseDto viewHistoryPageListResponseDto) {
        return new ViewHistoryPageListResponseVo(
                viewHistoryPageListResponseDto.isNext(),
                viewHistoryPageListResponseDto.isLast(),
                viewHistoryPageListResponseDto.getViewHistoryResponseDtoList()
        );
    }
}
