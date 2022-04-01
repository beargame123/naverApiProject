package com.example.restaurants.wishlist.repository;

import com.example.restaurants.db.MemoryDbRepositoryAbstract;
import com.example.restaurants.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}
