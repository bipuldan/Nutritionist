package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.Food;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class FoodRepositoryTest {

	@Autowired
	private  FoodRepository foodRepository;

	private Food food;
	
	@Before
	public void setupMock() {
		food = new Food(1, 12074, "Bipuldan", "ChickenButterMasala", "Kathi", "tests very nice");
		foodRepository.save(food);
	}
	@After
	public void dropdatabase(){
		foodRepository.deleteAllInBatch();
	}

	@Test
	public void testSaveFood() throws Exception {
		foodRepository.save(food);
		List<Food> foodList = foodRepository.findAll();
		Food Food = foodList.get(0);
		assertThat(Food.getName()).isEqualTo("ChickenButterMasala");
	}

	@Test
	public void testUpdateFood() throws Exception {
		List<Food> foodList = foodRepository.findAll();
		Food Food = foodList.get(0);
		assertEquals("ChickenButterMasala", Food.getName());
		Food.setComment("Tasty");
		List<Food> tempFoodList = foodRepository.findAll();
		Food tempFood = tempFoodList.get(0);
		assertEquals("Tasty", tempFood.getComment());
	}

	@Test
	public void testGetMovie() throws Exception {
		List<Food> foodList = foodRepository.findAll();
		Food Food = foodList.get(0);
		assertEquals("ChickenButterMasala", Food.getName());
	}

	@Test
	public void testGetMyFavouriteFood() throws Exception {
		List<Food> foods = foodRepository.findByUserId("Bipuldan");
		assertEquals("ChickenButterMasala", foods.get(0).getName());
	}

	@Test
	public void testDeleteMovie() throws Exception {
		List<Food> foods = foodRepository.findAll();
		Food Food = foods.get(0);
		foodRepository.delete(Food);
		assertThat(foodRepository.findById(1)).isEqualTo(Optional.empty());
	}
}
