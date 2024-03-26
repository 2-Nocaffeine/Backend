package com.nocaffeine.ssgclone.like.application;


import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.common.exception.BaseResponseStatus;
import com.nocaffeine.ssgclone.like.domain.LikeFolder;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.infrastructure.LikeFolderRepository;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderAddVo;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXIST_MEMBERS;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeFolderServiceImpl implements LikeFolderService{

    private final LikeFolderRepository likeFolderRepository;
    private final MemberRepository memberRepository;

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

}
