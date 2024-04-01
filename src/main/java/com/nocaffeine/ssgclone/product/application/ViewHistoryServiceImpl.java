package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.domain.ViewHistory;
import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;
import com.nocaffeine.ssgclone.product.dto.ViewHistoryListDto;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewHistoryServiceImpl implements ViewHistoryService {

    private final ViewHistoryRepository viewHistoryRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 최근 본 상품 목록을 조회하는 메소드
    @Override
    public List<ViewHistoryDto> getViewHistory(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<ViewHistory> viewHistories = viewHistoryRepository.findByMemberOrderByCreatedAtDesc(member);

        if (viewHistories.isEmpty()) {
            throw new BaseException(NO_VIEW_HISTORY);
        }

        List<ViewHistoryDto> responses = new ArrayList<>();

        for (ViewHistory viewHistory : viewHistories) {
            ViewHistoryDto response = ViewHistoryDto.builder()
                    .id(viewHistory.getId())
                    .productId(viewHistory.getProduct().getId())
                    .build();

            responses.add(response);
        }

        return responses;
    }

    @Override
    @Transactional
    public void addViewHistory(String memberUuid, ViewHistoryDto viewHistoryDto) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));
        Product product = productRepository.findById(viewHistoryDto.getProductId())
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        // 기존의 view history 에 해당 상품이 있다면 삭제
        viewHistoryRepository.deleteByMemberAndProduct(member, product);

        // 새롭게 view history 에 최근 본 상품 추가
        ViewHistory viewHistory = ViewHistory.builder()
                .member(member)
                .product(product)
                .build();

        viewHistoryRepository.save(viewHistory);
    }

    @Override
    @Transactional
    public void removeViewHistorys(String memberUuid, ViewHistoryListDto viewHistoryListDto) {
        // 회원이 존재하는지 확인
        // orElseThrow 를 사용하여 선언 타입을 Optional 을 쓰지않고 바로 Member 객체로 받을 수 있음
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        // view history 에서 삭제할 상품이 존재하는지 확인
        // ViewHistoryListDto 에서 리스트 형식으로 받은 productIds 를 하나씩 조회하여 삭제
        // for-each 문은 리스트에서 하나씩 꺼내와서 사용할 때 사용
        for (Long productId : viewHistoryListDto.getProductIds()) {

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new BaseException(NO_PRODUCT));

            ViewHistory viewHistory = viewHistoryRepository.findByMemberAndProduct(member, product)
                    .orElseThrow(() -> new BaseException(NO_EXIST_VIEW_HISTORY_PRODUCT));

            viewHistoryRepository.delete(viewHistory);
        }
    }

}
