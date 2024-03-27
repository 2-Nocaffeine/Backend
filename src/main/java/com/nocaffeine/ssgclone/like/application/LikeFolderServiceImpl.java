package com.nocaffeine.ssgclone.like.application;


import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.like.domain.LikeFolder;
import com.nocaffeine.ssgclone.like.domain.ProductLike;
import com.nocaffeine.ssgclone.like.dto.LikeAddFolderDto;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.infrastructure.LikeFolderRepository;
import com.nocaffeine.ssgclone.like.infrastructure.ProductLikeRepository;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeFolderServiceImpl implements LikeFolderService{

    private final LikeFolderRepository likeFolderRepository;
    private final MemberRepository memberRepository;
    private final ProductLikeRepository productLikeRepository;

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

        LikeFolder likeFolder = likeFolderRepository.findById(likeFolderDto.getLikeFolderId())
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
                    .likeFolderId(folder.getId())
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

        LikeFolder likeFolder = likeFolderRepository.findById(likeFolderDto.getLikeFolderId())
                .orElseThrow(() -> new BaseException(NO_DATA));

        likeFolder.changeName(likeFolderDto.getName());
    }


    /**
     * 폴더에 좋아요 상품 추가
     */
    @Override
    @Transactional
    public void addProductToLikeFolder(LikeAddFolderDto likeAddFolderDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        for (Long likeFolderId : likeAddFolderDto.getLikeFolderId()){
            LikeFolder likeFolder = likeFolderRepository.findById(likeFolderId)
                    .orElseThrow(() -> new BaseException(NO_EXIST_WISH_FOLDER));

            for (Long productLikeId : likeAddFolderDto.getProductLikeId()) {
                ProductLike productLike = productLikeRepository.findById(productLikeId)
                        .orElseThrow(() -> new BaseException(NO_DATA));

                // 폴더에 이미 있을경우 예외처리
                try {
                    productLikeRepository.findByProductAndLikeFolder(productLike.getProduct(), likeFolder.getId())
                            .ifPresent(like -> {
                                throw new BaseException(ALREADY_ADDED_FOLDER);
                            });
                } catch (Exception e) {
                    continue;
                }

                ProductLike like = ProductLike.builder()
                        .member(member)
                        .likeFolder(likeFolder.getId())
                        .product(productLike.getProduct())
                        .build();

                productLikeRepository.save(like);
            }
        }
    }
}
