package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.AddOptionDto;
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

    public static List<AddOptionResponseVo> addOptionDtoToVo(List<AddOptionDto> getAddOptions) {

        List<AddOptionResponseVo> addOptionResponseVo = new ArrayList<>();

        for (AddOptionDto addOptionDto : getAddOptions) {
            addOptionResponseVo.add(new AddOptionResponseVo(
                    addOptionDto.getId(),
                    addOptionDto.getOptionName()
            ));
        }

        return addOptionResponseVo;
    }
}
