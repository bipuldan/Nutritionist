package com.stackroute.favouriteservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.service.FoodService;


@RunWith(SpringRunner.class)
@WebMvcTest(FoodController.class)
public class FoodControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private FoodService service;

	private Food movie;

	@InjectMocks
	private FoodController controller;

	static List<Food> foods;
	
	private Food food;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		mvc = MockMvcBuilders.standaloneSetup(controller).build();
		foods = new ArrayList<>();
		food = new Food(1, 12074, "Bipuldan", "ChickenButterMasala", "Kathi", "tests very nice");
		foods.add(movie);
		food = new Food(2, 12074, "Bipuldan", "Biryani", "Kathi", "tests worst");
		foods.add(movie);
	}

	@Test
	public void testsaveFavoriteFoodSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJCaXB1bGRhbiIsImlhdCI6MTU1NjE5ODc3NH0.NYCYOWuBGOt0_QEbEWKqCGczSMtUeZmPJ_8eJyjZaWh29S9wPJhKRr1CeXjpBqWD";
		
		when(service.saveFood(food)).thenReturn(true);
		mvc.perform(post("/api/v1/foodservice/food").header("authorization", "Bearer " + token).
				contentType(MediaType.APPLICATION_JSON).content(jsonToString(food)))
		.andExpect(status().isCreated());
		verify(service, times(1)).saveFood(Mockito.any(Food.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testUpdateFoodSuccess() throws Exception {
		food.setComment("not so good food");
		when(service.updateFood(food)).thenReturn(foods.get(0));
		mvc.perform(put("/api/v1/foodservice/food/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonToString(food)))
		.andExpect(status().isOk());
		verify(service, times(1)).updateFood(Mockito.any(Food.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testDeleteFoodById() throws Exception {
		when(service.deleteFoodById(1)).thenReturn(true);
		mvc.perform(delete("/api/v1/foodservice/food/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).deleteFoodById(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetFoodById() throws Exception {
		when(service.getFoodById(1)).thenReturn(foods.get(0));
		mvc.perform(get("/api/v1/foodservice/food/{id}", 1)).andExpect(status().isOk());
		verify(service, times(1)).getFoodById(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testGetMyFavouriteFoods() throws Exception {
		String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJCaXB1bGRhbiIsImlhdCI6MTU1NjE5ODc3NH0.NYCYOWuBGOt0_QEbEWKqCGczSMtUeZmPJ_8eJyjZaWh29S9wPJhKRr1CeXjpBqWD";
		when(service.getMyFavouriteFood("Bipuldan")).thenReturn(null);
		mvc.perform(get("/api/v1/foodservice/food").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(service, times(1)).getMyFavouriteFood("Bipuldan");
		verifyNoMoreInteractions(service);
	}

	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}
}
