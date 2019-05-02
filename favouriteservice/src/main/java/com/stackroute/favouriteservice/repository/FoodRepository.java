package com.stackroute.favouriteservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.domain.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer>{
	
	public List<Food> findByUserId(String userId);
	

}
