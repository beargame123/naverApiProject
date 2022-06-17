package com.example.restaurants.wishlist.service;

import com.example.restaurants.naver.NaverClient;
import com.example.restaurants.naver.dto.SearchImageReq;
import com.example.restaurants.naver.dto.SearchLocalReq;
import com.example.restaurants.wishlist.dto.WishListDto;
import com.example.restaurants.wishlist.entity.WishListEntity;
import com.example.restaurants.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query){
        // 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() > 0){
            var localItem = searchLocalRes.getItems().stream().findFirst().get();
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과를 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());
                return result;
            }
        }

        return new WishListDto();
    }

    public void add(WishListDto wishListDto) {
        if(wishListDto.getId() == null){
            Integer i =0;
            i++;
            wishListDto.setId(i);
        }
        WishListEntity wishListEntity = WishListEntity.builder()
                .id(wishListDto.getId())
                .title(wishListDto.getTitle())
                .category(wishListDto.getCategory())
                .address(wishListDto.getAddress())
                .roadAddress(wishListDto.getRoadAddress())
                .homePageLink(wishListDto.getHomePageLink())
                .imageLink(wishListDto.getImageLink())
                .isVisit(wishListDto.isVisit())
                .visitCount(wishListDto.getVisitCount())
                .lastVisitDate(wishListDto.getLastVisitDate())
                .build();
        log.info("{}", wishListDto);
        wishListRepository.save(wishListEntity);
    }

    public List<WishListEntity> findAll() {
        return wishListRepository.findAll();
    }

    public void delete(Integer id) {
        wishListRepository.deleteById(id);
    }

    public void addVisit(Integer id){
        WishListEntity wishItem = wishListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
        wishItem.set(true, wishItem.getVisitCount()+1 );
    }
}