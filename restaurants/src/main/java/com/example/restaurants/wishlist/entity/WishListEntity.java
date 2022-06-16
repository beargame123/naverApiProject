package com.example.restaurants.wishlist.entity;

import com.example.restaurants.db.MemoryDbEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class WishListEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;  //음식점
    private String category; // 카테고리
    private String address; // 주소
    private String roadAddress; // 도로명
    private String homePageLink; // 홈페이지 주소
    private String imageLink; // 음식, 가게명 주소
    private boolean isVisit; // 방문 여부
    private int visitCount; // 방문 카운트
    private LocalDateTime lastVisitDate; // 마지막 방문 일자
}
