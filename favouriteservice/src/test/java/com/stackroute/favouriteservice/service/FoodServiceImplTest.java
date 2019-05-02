package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.repository.FoodRepository;


public class FoodServiceImplTest {

	@Mock
	private FoodRepository foodRepository;

	private Food food;

	@InjectMocks
	private FoodServiceImpl foodServiceImpl;

	Optional<Food> options;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		food = new Food(1, 12074, "Bipuldan", "chickenButter masala", "Kathi", "tests very nice");
		options = Optional.of(food);
	}


	@Test
	public void testMockCreation() {
		assertNotNull("JpaRepository creation failed: use @InjectMocks on foodServiceImpl", food);
	}

	@Test
	public void testSaveFoodSuccess() throws FoodAlreadyExistsException {
		when(foodRepository.save(food)).thenReturn(food);
		final boolean flag = foodServiceImpl.saveFood(food);
		assertTrue("saving food failed", flag);
		verify(foodRepository, times(1)).save(food);
	}

	@Test(expected = FoodAlreadyExistsException.class)
	public void testSaveFoodFailure() throws FoodAlreadyExistsException {
		when(foodRepository.findById(1)).thenReturn(options);
		when(foodRepository.save(food)).thenReturn(food);
		final boolean flag = foodServiceImpl.saveFood(food);
		assertTrue("saving food failed", flag);
		verify(foodRepository, times(1)).findById(food.getId());
	}

	@Test
	public void testUpdateFood() throws FoodNotFoundException {
		when(foodRepository.findById(1)).thenReturn(options);
		when(foodRepository.save(food)).thenReturn(food);
		food.setComment("not so good food");
		Food food1 = foodServiceImpl.updateFood(food);
		assertEquals("updaing food failed", food.getComment(), food1.getComment());
		verify(foodRepository, times(1)).save(food);
		verify(foodRepository, times(1)).findById(food.getId());
	}

		@Test
	public void testGetFoodById() throws FoodNotFoundException {
		when(foodRepository.findById(1)).thenReturn(options);
		Food food1 = foodServiceImpl.getFoodById(1);
		assertEquals("fetching food by id failed", food, food1);
		verify(foodRepository, times(1)).findById(food.getId());
	}

	@Test
	public void testGetAllFood() throws FoodNotFoundException {
		List<Food> foods = new ArrayList<>();
		foods.add(food);
		when(foodRepository.findByUserId("Bipuldan")).thenReturn(foods);
		List<Food> foods1 = foodServiceImpl.getMyFavouriteFood("Bipuldan");
		assertEquals(foods, foods1);
		verify(foodRepository, times(1)).findByUserId("Bipuldan");
		
	}
	
	@Test
	public void testDeleteFoodById() throws FoodNotFoundException {
		when(foodRepository.findById(1)).thenReturn(options);
		doNothing().when(foodRepository).delete(food);
		food.setComment("not so good food");
		final boolean flag = foodServiceImpl.deleteFoodById(1);
		assertTrue("deleting food failed", flag);
		verify(foodRepository, times(1)).delete(food);
		verify(foodRepository, times(1)).findById(food.getId());
	}



}