package com.example.restaurants.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {

    private Integer id;
    private String title;  // 장소이름
    private String category; // 카테고리
    private String address; // 주소
    private String roadAddress; // 도로명
    private String homePageLink; // 홈페이지 주소
    private String imageLink; // 음식, 가게명 주소
    private boolean isVisit; // 방문 여부
    private int visitCount; // 방문 카운트
    private LocalDateTime lastVisitDate; // 마지막 방문 일자
}
