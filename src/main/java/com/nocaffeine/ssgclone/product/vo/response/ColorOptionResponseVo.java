package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.ColorOptionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ColorOptionResponseVo {
    
    private Long id;
    private String color;

    public ColorOptionResponseVo(Long id, String color) {
        this.id = id;
        this.color = color;
    }
    
    public static List<ColorOptionResponseVo> colorOptionDtoToVo(List<ColorOptionDto> getColorOptions) {
        
        List<ColorOptionResponseVo> colorOptionResponseVo = new ArrayList<>();

        for (ColorOptionDto colorOptionDto : getColorOptions) {
            colorOptionResponseVo.add(new ColorOptionResponseVo(
                    colorOptionDto.getId(),
                    colorOptionDto.getColor()
            ));
        }
        
        return colorOptionResponseVo;
    }
}
