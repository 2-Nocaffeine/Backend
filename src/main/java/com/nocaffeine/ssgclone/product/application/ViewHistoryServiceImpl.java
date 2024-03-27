package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.product.domain.ViewHistory;
import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;
import com.nocaffeine.ssgclone.product.infrastructure.ViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_VIEW_HISTORY;

@Service
@RequiredArgsConstructor
public class ViewHistoryServiceImpl implements ViewHistoryService {

    private final ViewHistoryRepository viewHistoryRepository;
    private final MemberRepository memberRepository;

    // 최근 본 상품 목록을 조회하는 메소드
    @Override
    public List<ViewHistoryDto> getViewHistory(String memberUuid) {
        List<ViewHistory> viewHistories = viewHistoryRepository.findByMemberUuid(memberUuid);
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


}
