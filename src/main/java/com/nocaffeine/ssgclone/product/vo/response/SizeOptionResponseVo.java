package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.SizeOptionDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SizeOptionResponseVo {

    private final Long id;
    private final String size;

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
