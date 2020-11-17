package com.santa.xgos.repository;

import com.santa.xgos.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, String> {
}
