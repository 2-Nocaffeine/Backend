package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.SizeOptionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SizeOptionResponseVo {

    private Long id;
    private String size;

    public SizeOptionResponseVo(Long id, String size) {
        this.id = id;
        this.size = size;
    }

    public static List<SizeOptionResponseVo> sizeOptionDtoToVo(List<SizeOptionDto> getSizeOptions) {

        List<SizeOptionResponseVo> sizeOptionResponseVo = new ArrayList<>();

        for (SizeOptionDto sizeOptionDto : getSizeOptions) {
            sizeOptionResponseVo.add(new SizeOptionResponseVo(
                    sizeOptionDto.getId(),
                    sizeOptionDto.getSize()
            ));
        }

        return sizeOptionResponseVo;
    }
}
