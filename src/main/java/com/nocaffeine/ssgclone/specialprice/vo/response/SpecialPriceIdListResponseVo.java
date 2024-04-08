package com.nocaffeine.ssgclone.specialprice.vo.response;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceIdListResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpecialPriceIdListResponseVo {

    private Long specialId;

    private SpecialPriceIdListResponseVo(Long specialId) {
        this.specialId = specialId;
    }

    public static List<SpecialPriceIdListResponseVo> convertToVo(List<SpecialPriceIdListResponseDto> specialPriceIdList) {
        List<SpecialPriceIdListResponseVo> specialPriceIdListResponseVos = new ArrayList<>();
        for (SpecialPriceIdListResponseDto specialPriceIds : specialPriceIdList){
            specialPriceIdListResponseVos.add(new SpecialPriceIdListResponseVo(specialPriceIds.getSpecialId()));
        }
        return specialPriceIdListResponseVos;
    }
}
