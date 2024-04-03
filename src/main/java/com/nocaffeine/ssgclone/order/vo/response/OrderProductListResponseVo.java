package com.nocaffeine.ssgclone.order.vo.response;

import com.nocaffeine.ssgclone.order.dto.response.OrderIdListResponseDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderProductListResponseDto;
import lombok.Getter;

@Getter
public class OrderProductListResponseVo {

    private String productName;
    private String addOption;
    private String color;
    private String size;
    private int count;
    private int price;
    private String brand;
    private String thumbnail;

    public OrderProductListResponseVo(String productName, String addOption, String color, String size, int count, int price, String brand, String thumbnail) {
        this.productName = productName;
        this.addOption = addOption;
        this.color = color;
        this.size = size;
        this.count = count;
        this.price = price;
        this.brand = brand;
        this.thumbnail = thumbnail;
    }

    public static OrderProductListResponseVo convertToVo(OrderProductListResponseDto orderProductListResponseDto) {
        return new OrderProductListResponseVo(
                orderProductListResponseDto.getProductName(),
                orderProductListResponseDto.getAddOption(),
                orderProductListResponseDto.getColor(),
                orderProductListResponseDto.getSize(),
                orderProductListResponseDto.getCount(),
                orderProductListResponseDto.getPrice(),
                orderProductListResponseDto.getBrand(),
                orderProductListResponseDto.getThumbnail()
        );
    }

}
