package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.Food;
import com.stackroute.favouriteservice.exception.FoodAlreadyExistsException;
import com.stackroute.favouriteservice.exception.FoodNotFoundException;
import com.stackroute.favouriteservice.service.FoodService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/v1/foodservice")
@CrossOrigin
public class FoodController {
	
	@Autowired
	private FoodService FoodService;
	
	/*
	 * Saving Food in the Favourite Food list on the basis of userId 
	 */
	
	@PostMapping("/food")
	public ResponseEntity<?> saveFavoriteFood(@RequestBody Food food, HttpServletRequest request,
			final HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		
		try{
			int ndbno = food.getNdbno();
			food.setUserId(userId);
			FoodService.saveFood(food);
			responseEntity = new ResponseEntity<Food>(food, HttpStatus.CREATED);
		} catch (FoodAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	/*
	 * updating food comment as per foodId bookmarked in favourite food list
	 */
	
	@PutMapping(path = "/food/{id}")
	public ResponseEntity<?> updatefood(@PathVariable("id") final Integer id, @RequestBody Food food) {
		ResponseEntity<?> responseEntity;
		try {
			Food fetchedFood = FoodService.updateFood(food);
			responseEntity = new ResponseEntity<Food>(fetchedFood, HttpStatus.OK);
		} catch (FoodNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	/*
	 * Deleting food present in the favourite food list on the basis of foodId of a particular user
	 */

	@DeleteMapping(path = "/food/{id}")
	public ResponseEntity<?> deleteFoodById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			FoodService.deleteFoodById(id);
			responseEntity = new ResponseEntity<String>("food deleted successfully", HttpStatus.OK);
		} catch (FoodNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/*
	 * fetching foods from api 
	 */
	
	@GetMapping(path = "/food/{id}")
	public ResponseEntity<?> getfoodById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			Food food = FoodService.getFoodById(id);
			responseEntity = new ResponseEntity<Food>(food, HttpStatus.OK);
		} catch (FoodNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/*
	 * fetching my favourite food list 
	 */
	
	@GetMapping("/food")
	public ResponseEntity<?> getMyfoods(final HttpServletRequest request, final HttpServletResponse response) {
		
		String authHeader = request.getHeader("authorization");
		String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		List<Food> foods = FoodService.getMyFavouriteFood(userId);
		ResponseEntity<?> responseEntity = new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
		return responseEntity;
	}
	
}


