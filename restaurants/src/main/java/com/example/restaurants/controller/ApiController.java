package com.example.restaurants.controller;

import com.example.restaurants.wishlist.dto.WishListDto;
import com.example.restaurants.wishlist.entity.WishListEntity;
import com.example.restaurants.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController{

    private final WishListService wishListService;

    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){
        return wishListService.search(query);
    }

    @PostMapping("")
    public void add(@RequestBody WishListDto wishListDto){
        wishListService.add(wishListDto);
    }

    @GetMapping("/all")
    public List<WishListEntity> findAll(){

        return wishListService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        wishListService.delete(id);
    }

    @PostMapping("/{id}")
    public void addVisit(@PathVariable Integer id){
        wishListService.addVisit(id);
    }


}
