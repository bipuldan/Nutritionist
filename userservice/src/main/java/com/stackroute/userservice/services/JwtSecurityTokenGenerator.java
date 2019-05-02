package com.stackroute.userservice.services;

import java.util.Map;

import com.stackroute.userservice.domain.User;

public interface JwtSecurityTokenGenerator {
	
	Map<String, String> generateToken(User user);
}
