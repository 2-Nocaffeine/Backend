package com.nocaffeine.ssgclone.product.vo.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ViewHistoryDeleteRequestVo {

    private List<Long> productIds;
}
