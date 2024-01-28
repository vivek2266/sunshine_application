package com.dl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dl.annotation.TrackExecutionTime;
import com.dl.entity.Users;
import com.dl.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	 @TrackExecutionTime
	@Operation(summary = "add a new user to the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "user added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserService.class)) }),
			@ApiResponse(responseCode = "400", description = "validation error") })
	@PostMapping("/addUsers")
	public Users addUser(@RequestBody Users entity) {
		return userService.addUser(entity);
	}
	 @TrackExecutionTime
	@Operation(summary = "Fetch all user object")

	@GetMapping("/getAllUsers")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
	 @TrackExecutionTime
	@Operation(summary = "Fetch all getAllActiveUsers object")
	@GetMapping("/getAllActiveUsers")
	public List<Users> getAllActiveUsers() {
		return userService.getAllActiveUsers();
	}
	 @TrackExecutionTime
	@Operation(summary = " getUserById by userId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "getUserById found successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserService.class)) }),
			@ApiResponse(responseCode = "400", description = "user not found with given details") })
	@GetMapping("/getById/{userId}")
	public Optional<Users> getUsersById(@PathVariable int userId) {
		return userService.getUsersById(userId);
	}

	@Operation(summary = "Delete deleteUser by userId")
	@DeleteMapping("/delete/{userId}")
	public Users deleteUserById(@PathVariable int userId) {
		return userService.deactivateUserById(userId);
	}
	 @TrackExecutionTime
	@Operation(summary = "updateUser by userId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "updateUser added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserService.class)) }),
			@ApiResponse(responseCode = "400", description = "user not found with given details") })
	@PutMapping("/update/{userId}")
	public Users updateUser(@PathVariable int userId, @RequestBody Users users) {
		return userService.updateUser(userId, users);
	}
	 @TrackExecutionTime
	@Operation(summary = "get FullUserDetails by userId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "FullUserDetails found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserService.class)) }),
			@ApiResponse(responseCode = "400", description = "users not found with given details") })
	@GetMapping("/getFullUserDetails/{userId}")
	public Users getFullUserDetails(@PathVariable Integer userId) {
		return userService.getFullUserDetails(userId);
	}

	@Operation(summary = "add roles to users into the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "RolesToUsers added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserService.class)) }),
			@ApiResponse(responseCode = "400", description = "RolesOrUsers not found with given details") })
	 @TrackExecutionTime
	@PutMapping("/{userId}/assignRoles/{roleId}")
	public Users assignRolesToUsers(@PathVariable int userId, @PathVariable int roleId) {

		return userService.assignRolesToUsers(userId, roleId);
	}
	 @TrackExecutionTime
	@Operation(summary = "assignStoresToUsers into the system")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "assignStoresToUsers added successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserService.class)) }),
			@ApiResponse(responseCode = "400", description = "StoresOrUsers not found with given details") })
	@PutMapping("/{userId}/assignStores/{storeId}")
	public Users assignStoresToUsers(@PathVariable int userId, @PathVariable int storeId) {

		return userService.assignStoresToUsers(userId, storeId);
	}

}
