package com.nocaffeine.ssgclone.product.vo.response.productoption;

import com.nocaffeine.ssgclone.product.dto.response.productoption.SizeOptionResponseDto;
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

    public static List<SizeOptionResponseVo> sizeOptionDtoToVo(List<SizeOptionResponseDto> getSizeOptions) {

        List<SizeOptionResponseVo> sizeOptionResponseVo = new ArrayList<>();

        for (SizeOptionResponseDto sizeOptionResponseDto : getSizeOptions) {
            sizeOptionResponseVo.add(new SizeOptionResponseVo(
                    sizeOptionResponseDto.getId(),
                    sizeOptionResponseDto.getSize()
            ));
        }

        return sizeOptionResponseVo;
    }
}
