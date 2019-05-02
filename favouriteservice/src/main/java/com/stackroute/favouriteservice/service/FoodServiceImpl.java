package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private  FoodRepository foodRepository;

	@Override
	public boolean saveFood(Food food) throws FoodAlreadyExistsException {
		final Optional<Food> object = foodRepository.findById(food.getId());
		if (object.isPresent()) {
			throw new FoodAlreadyExistsException("Unable to save food, Food already exists");
		}
		foodRepository.save(food);
		return true;
	}

	@Override
	public Food updateFood(Food updateFood) throws FoodNotFoundException {
		final Food food = foodRepository.findById(updateFood.getId()).orElse(null);
		if (food == null) {
			throw new FoodNotFoundException("Unable to update. Food not found");
		}
		food.setComment(updateFood.getComment());
		foodRepository.save(food);
		return food;
	}

	@Override
	public boolean deleteFoodById(int id) throws FoodNotFoundException {
		final Food food = foodRepository.findById(id).orElse(null);
		if (food == null) {
			throw new FoodNotFoundException("Unable to delete. Food not found");
		}
		foodRepository.delete(food);
		return true;
	}

	@Override
	public Food getFoodById(int id) throws FoodNotFoundException {
		final Food food = foodRepository.findById(id).get();
		if (food == null) {
			throw new FoodNotFoundException("Food not found");
		}
		return food;
	}


	@Override
	public List<Food> getMyFavouriteFood(String userId) {
		return foodRepository.findByUserId(userId);
	}

}
