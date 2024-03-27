package com.nocaffeine.ssgclone.like.application;


import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.like.domain.LikeFolder;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.infrastructure.LikeFolderRepository;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_DATA;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXIST_MEMBERS;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeFolderServiceImpl implements LikeFolderService{

    private final LikeFolderRepository likeFolderRepository;
    private final MemberRepository memberRepository;

    /**
     * 좋아요 폴더 추가
     */
    @Override
    @Transactional
    public void addLikeFolder(LikeFolderDto likeFolderDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        LikeFolder likeFolder = LikeFolder.builder()
                .name(likeFolderDto.getName())
                .member(member)
                .build();

        likeFolderRepository.save(likeFolder);
    }

    /**
     * 좋아요 폴더 삭제
     */
    @Override
    @Transactional
    public void removeLikeFolder(LikeFolderDto likeFolderDto, String memberUuid) {
        memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        LikeFolder likeFolder = likeFolderRepository.findById(likeFolderDto.getId())
                .orElseThrow(() -> new BaseException(NO_DATA));

        likeFolderRepository.delete(likeFolder);
    }

    /**
     * 좋아요 폴더 조회
     */
    @Override
    public List<LikeFolderDto> findLikeFolderList(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<LikeFolder> likeFolder = likeFolderRepository.findByMember(member);

        List<LikeFolderDto> responses = new ArrayList<>();

        for (LikeFolder folder : likeFolder) {
            LikeFolderDto likeFolderDto = LikeFolderDto.builder()
                    .id(folder.getId())
                    .name(folder.getName())
                    .build();

            responses.add(likeFolderDto);
        }
        return responses;
    }


    /**
     * 좋아요 폴더 이름 수정
     */
    @Override
    @Transactional
    public void modifyLikeFolder(LikeFolderDto likeFolderDto, String memberUuid) {
        memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        LikeFolder likeFolder = likeFolderRepository.findById(likeFolderDto.getId())
                .orElseThrow(() -> new BaseException(NO_DATA));

        likeFolder.changeName(likeFolderDto.getName());
    }



}
