package com.gotodigital.covid19.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.gotodigital.covid19.api.exception.BadRequestException;
import com.gotodigital.covid19.api.exception.ResourceNotFoundException;
import com.gotodigital.covid19.api.model.User;
import com.gotodigital.covid19.api.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	@CrossOrigin
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/user/{id}")
	@CrossOrigin
	public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for id: " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/user")
	@CrossOrigin
	public User createUser(@Valid @RequestBody User user) throws BadRequestException {
			return userRepository.save(user);
	}

	@PutMapping("/user/{id}")
	@Transactional
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for id:" + userId));

		user.setName(userDetails.getName());
		user.setLastName(userDetails.getLastName());
		user.setAddress(userDetails.getAddress());
		user.setCity(userDetails.getCity());
		return ResponseEntity.ok(userRepository.save(user));
	}

	@DeleteMapping("/user/{id}")
	@CrossOrigin
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
