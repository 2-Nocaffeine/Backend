package com.nocaffeine.ssgclone.product.vo.response.productoption;

import com.nocaffeine.ssgclone.product.dto.response.productoption.AddOptionResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AddOptionResponseVo {

    private Long id;
    private String optionName;

    public AddOptionResponseVo(Long id, String optionName) {
        this.id = id;
        this.optionName = optionName;
    }

    public static List<AddOptionResponseVo> addOptionDtoToVo(List<AddOptionResponseDto> getAddOptions) {

        List<AddOptionResponseVo> addOptionResponseVo = new ArrayList<>();

        for (AddOptionResponseDto addOptionResponseDto : getAddOptions) {
            addOptionResponseVo.add(new AddOptionResponseVo(
                    addOptionResponseDto.getId(),
                    addOptionResponseDto.getOptionName()
            ));
        }

        return addOptionResponseVo;
    }
}
