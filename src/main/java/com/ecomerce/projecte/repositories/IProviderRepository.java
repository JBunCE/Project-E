package com.ecomerce.projecte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.projecte.entities.Provider;

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Long>{
    
}
