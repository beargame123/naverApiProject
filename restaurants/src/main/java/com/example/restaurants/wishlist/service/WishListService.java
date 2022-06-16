package com.example.restaurants.wishlist.service;

import com.example.restaurants.naver.NaverClient;
import com.example.restaurants.naver.dto.SearchImageReq;
import com.example.restaurants.naver.dto.SearchLocalReq;
import com.example.restaurants.wishlist.dto.WishListDto;
import com.example.restaurants.wishlist.entity.WishListEntity;
import com.example.restaurants.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }


    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = WishListEntity.builder()
                .id(wishListDto.getIndex())
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
        return entity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getId());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        return dto;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public void delete(int id) {
        wishListRepository.deleteById(id);
    }

    public void addVisit(int id){
        var wishItem = wishListRepository.findById(id);
        if(wishItem.isPresent()){
            var item = wishItem.get();
            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}