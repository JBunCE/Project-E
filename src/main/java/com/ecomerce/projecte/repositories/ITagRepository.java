package com.ecomerce.projecte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.projecte.entities.Tag;


@Repository
public interface ITagRepository extends JpaRepository<Tag, Long>{
    
}
