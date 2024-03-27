package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.AddOptionDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AddOptionResponseVo {

    private final Long id;
    private final String optionName;

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
