package com.jpa.services;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TestApi {

	@RequestMapping("api/test")
	public String testing() {
		return "Hello from the api";
	}
}
