package com.shannerterman.controller;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shannerterman.entity.ApplicationUser;
import com.shannerterman.service.ApplicationUserService;

@RestController	
@RequestMapping
public class ApplicationUserController {
	
	@Autowired
	private ApplicationUserService applicationUserService;

	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createApplicationUser(@RequestBody ApplicationUser applicationUser) {
		applicationUserService.createApplicationUser(applicationUser);
	}
	
	@GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<ApplicationUser> getAllUsers() {
    	return applicationUserService.getAllUsers();
    }
	
	@GetMapping(value = "/logout")
	public String logoutUser() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "Session Ended";
	}
	
	
}
