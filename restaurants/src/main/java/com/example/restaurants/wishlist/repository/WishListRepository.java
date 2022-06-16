package com.example.restaurants.wishlist.repository;

import com.example.restaurants.wishlist.entity.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishListEntity, Integer> {
}
