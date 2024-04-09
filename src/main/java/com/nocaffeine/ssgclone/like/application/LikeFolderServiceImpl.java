package com.nocaffeine.ssgclone.like.application;


import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.like.domain.LikeFolder;
import com.nocaffeine.ssgclone.like.domain.ProductLike;
import com.nocaffeine.ssgclone.like.dto.ProductLikeAddDto;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.dto.ProductLikeListDto;
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
     * 폴더 추가
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
    public void removeLikeFolder(Long likeFolderId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        LikeFolder likeFolder = likeFolderRepository.findByIdAndMember(likeFolderId, member)
                .orElseThrow(() -> new BaseException(NO_DATA));

        likeFolderRepository.delete(likeFolder);
    }

    /**
     * 좋아요 폴더 조회
     */
    @Override
    public List<LikeFolderDto> findLikeFolder(String memberUuid) {
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
    public void modifyLikeFolder(LikeFolderDto likeFolderDto, Long likeFolderId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        LikeFolder likeFolder = likeFolderRepository.findByIdAndMember(likeFolderId, member)
                .orElseThrow(() -> new BaseException(NO_DATA));

        likeFolderRepository.save(LikeFolder.builder()
                .id(likeFolder.getId())
                .name(likeFolderDto.getName())
                .member(likeFolder.getMember())
                .build());
    }


    /**
     * 폴더에 좋아요 상품 추가
     */
    @Override
    @Transactional
    public void addProductLike(ProductLikeAddDto productLikeAddDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        for (Long likeFolderId : productLikeAddDto.getLikeFolderId()){
            LikeFolder likeFolder = likeFolderRepository.findById(likeFolderId)
                    .orElseThrow(() -> new BaseException(NO_EXIST_WISH_FOLDER));

            for (Long productLikeId : productLikeAddDto.getProductLikeId()) {
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

    /**
     * 폴더에 있는 상품 조회
     */
    @Override
    public List<ProductLikeListDto> findProductLike(Long likeFolderId, String memberUuid) {
        List<ProductLike> productLike = productLikeRepository.findByLikeFolder(likeFolderId);

        List<ProductLikeListDto> responses = new ArrayList<>();

        for (ProductLike like : productLike) {
            ProductLikeListDto productLikeListDto = ProductLikeListDto.builder()
                    .productLikeId(like.getId())
                    .productId(like.getProduct().getId())
                    .build();

            responses.add(productLikeListDto);
        }
        return responses;
    }

    /**
     * 폴더에 있는 상품 삭제
     */
    @Override
    @Transactional
    public void removeProductLike(List<Long> productLikeIds, Long likeFolderId) {
        for (Long productLikeId : productLikeIds) {
            ProductLike productLike = productLikeRepository.findByIdAndLikeFolder(productLikeId, likeFolderId)
                    .orElseThrow(() -> new BaseException(NO_DATA));

            productLikeRepository.delete(productLike);
        }
    }

}
