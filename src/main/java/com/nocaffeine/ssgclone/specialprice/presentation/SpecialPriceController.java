package com.nocaffeine.ssgclone.specialprice.presentation;
import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.specialprice.application.SpecialPriceService;
import com.nocaffeine.ssgclone.specialprice.vo.response.SpecialPriceDetailResponseVo;
import com.nocaffeine.ssgclone.specialprice.vo.response.SpecialPriceIdListResponseVo;
import com.nocaffeine.ssgclone.specialprice.vo.response.SpecialPriceInfoResponseVo;
import com.nocaffeine.ssgclone.specialprice.vo.response.SpecialPriceProductIdResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name="special", description = "특가")
@RequestMapping("api/v1/special")
public class SpecialPriceController {

    private final SpecialPriceService specialPriceService;

    @Operation(summary = "특가 id 나열", description = "특가 id 나열")
    @GetMapping
    public CommonResponse<List<SpecialPriceIdListResponseVo>> specialPriceList(){

        return CommonResponse.success("특가 목록을 성공적으로 찾았습니다.", SpecialPriceIdListResponseVo.convertToVo(specialPriceService.findSpecialPriceIds()));

    }

    @Operation(summary = "특가별 정보 나열", description = "특가별 정보 나열")
    @GetMapping("/{specialPriceId}/list")
    public CommonResponse<SpecialPriceInfoResponseVo>
        specialPriceInfoList(@PathVariable("specialPriceId") Long specialPriceId){

     return CommonResponse.success("특가별 정보를 성공적으로 찾았습니다.",
            SpecialPriceInfoResponseVo.convertToVo(specialPriceService.findSpecialPriceInfo(specialPriceId)));
 }

    @Operation(summary = "특가별 상품 id 나열", description = "특가별 상품 id 나열")
    @GetMapping("/{specialPriceId}/product-list")
    public CommonResponse<SpecialPriceDetailResponseVo> specialPriceProductList(@PathVariable("specialPriceId") Long specialPriceId){

        return CommonResponse.success("특가별 상품 id를 성공적으로 찾았습니다.", SpecialPriceDetailResponseVo.convertToVo(specialPriceService.findSpecialPriceProductList(specialPriceId)));
    }

}