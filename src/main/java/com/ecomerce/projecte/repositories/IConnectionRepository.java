package com.ecomerce.projecte.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomerce.projecte.entities.Connection;
import org.springframework.stereotype.Repository;

@Repository
public interface IConnectionRepository extends JpaRepository<Connection,Long>{

}
