package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.LikeFolderService;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderAddRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderModifyRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderRemoveVo;
import com.nocaffeine.ssgclone.like.vo.response.LikeFolderListResponseVo;
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

    @PostMapping
    public CommonResponse<String> addFolder(@RequestBody LikeFolderAddRequestVo likeFolderAddRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderAddRequestVo);
        likeFolderService.addLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 추가 성공");

    }

    @GetMapping
    public CommonResponse<List<LikeFolderListResponseVo>> getFolderList(){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());

        List<LikeFolderDto> likeFolderListDto = likeFolderService.findLikeFolderList(memberUuid);

        List<LikeFolderListResponseVo> likeFolderListResponseVo = new ArrayList<>();
        for (LikeFolderDto likeFolderDto : likeFolderListDto) {
            likeFolderListResponseVo.add(LikeFolderListResponseVo.dtoToVo(likeFolderDto));
        }

        return CommonResponse.success("폴더 조회 성공",likeFolderListResponseVo);
    }

    @DeleteMapping
    public CommonResponse<String> removeFolder(@RequestBody LikeFolderRemoveVo likeFolderRemoveVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderRemoveVo);
        likeFolderService.removeLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 삭제 성공");
    }

    @PatchMapping
    public CommonResponse<String> modifyFolder(@RequestBody LikeFolderModifyRequestVo likeFolderModifyRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        LikeFolderDto likeFolderDto = LikeFolderDto.voToDto(likeFolderModifyRequestVo);
        likeFolderService.modifyLikeFolder(likeFolderDto, memberUuid);
        return CommonResponse.success("폴더 수정 성공");
    }
}
