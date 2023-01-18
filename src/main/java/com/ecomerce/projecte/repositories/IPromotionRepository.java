package com.ecomerce.projecte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.projecte.entities.Promotion;

@Repository
public interface IPromotionRepository extends JpaRepository<Promotion, Long> {
    
}
