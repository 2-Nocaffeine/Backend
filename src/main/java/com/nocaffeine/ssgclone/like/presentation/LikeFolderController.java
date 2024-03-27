package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.LikeFolderService;
import com.nocaffeine.ssgclone.like.dto.ProductLikeAddDto;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.dto.ProductLikeListDto;
import com.nocaffeine.ssgclone.like.dto.ProductLikeRemoveDto;
import com.nocaffeine.ssgclone.like.vo.request.*;
import com.nocaffeine.ssgclone.like.vo.response.LikeFolderListResponseVo;
import com.nocaffeine.ssgclone.like.vo.response.LikeProductListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/like/folder")
public class LikeFolderController {

    private final JwtTokenProvider jwtTokenProvider;
    private final LikeFolderService likeFolderService;

    @Operation(summary = "폴더 추가", description = "폴더 추가", tags = {"like folder add"})
    @PostMapping
    public CommonResponse<String> folderAdd(@RequestBody LikeFolderAddRequestVo likeFolderAddRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderAddRequestVo);
        likeFolderService.addLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 추가 성공");

    }

    @Operation(summary = "폴더 조회", description = "폴더 조회", tags = {"like folder find"})
    @GetMapping
    public CommonResponse<List<LikeFolderListResponseVo>> folderList(){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());

        List<LikeFolderDto> likeFolderListDto = likeFolderService.findLikeFolder(memberUuid);

        List<LikeFolderListResponseVo> likeFolderListResponseVo = new ArrayList<>();
        for (LikeFolderDto likeFolderDto : likeFolderListDto) {
            likeFolderListResponseVo.add(LikeFolderListResponseVo.dtoToVo(likeFolderDto));
        }

        return CommonResponse.success("폴더 조회 성공",likeFolderListResponseVo);
    }

    @Operation(summary = "폴더 삭제", description = "폴더 삭제", tags = {"like folder remove"})
    @DeleteMapping
    public CommonResponse<String> folderRemove(@RequestBody LikeFolderRemoveRequestVo likeFolderRemoveRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderRemoveRequestVo);
        likeFolderService.removeLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 삭제 성공");
    }

    @Operation(summary = "폴더 이름 수정", description = "폴더 이름 수정", tags = {"like folder name modify"})
    @PatchMapping
    public CommonResponse<String> folderModify(@RequestBody LikeFolderModifyRequestVo likeFolderModifyRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderModifyRequestVo);
        likeFolderService.modifyLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 이름 수정 성공");
    }

    @Operation(summary = "선택한 폴더에 좋아요 상품 추가", description = "선택한 폴더에 좋아요 상품 추가", tags = {"product like add folder"})
    @PostMapping("/product")
    public CommonResponse<String> productLikeAdd(@RequestBody ProductLikeMoveRequestVo productLikeMoveRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        ProductLikeAddDto productLikeAddDto = ProductLikeAddDto.voToDto(productLikeMoveRequestVo);
        likeFolderService.addProductLike(productLikeAddDto, memberUuid);
        return CommonResponse.success("폴더 추가 성공");
    }

    @Operation(summary = "폴더에 담긴 상품 좋아요 조회", description = "폴더 담긴 상품 좋아요 조회", tags = {"product like folder list"})
    @GetMapping("/{likeFolderId}/product")
    public CommonResponse<List<LikeProductListResponseVo>> productLikeList(@PathVariable Long likeFolderId){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());

        List<ProductLikeListDto> likeFolderProductList = likeFolderService.findProductLike(likeFolderId, memberUuid);

        List<LikeProductListResponseVo> likeFolderListResponseVo = new ArrayList<>();
        for (ProductLikeListDto productLikeListDto : likeFolderProductList) {
            likeFolderListResponseVo.add(LikeProductListResponseVo.dtoToVo(productLikeListDto));
        }

        return CommonResponse.success("폴더 상품 조회 성공", likeFolderListResponseVo);
    }

    @Operation(summary = "폴더에 담긴 상품 좋아요 삭제", description = "폴더 담긴 상품 좋아요 삭제", tags = {"product like folder remove"})
    @DeleteMapping("/product")
    public CommonResponse<String> productLikeRemove(@RequestBody ProductRemoveRequestVo productRemoveRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        ProductLikeRemoveDto productLikeRemoveDto = ProductLikeRemoveDto.voToDto(productRemoveRequestVo);
        likeFolderService.removeProductLike(productLikeRemoveDto, memberUuid);
        return CommonResponse.success("폴더 삭제 성공");
    }



}
