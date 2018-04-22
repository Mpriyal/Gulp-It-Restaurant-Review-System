package com.jpa.services;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.EventDao;

@CrossOrigin
@RestController
public class EventService {

	EventDao evDao = EventDao.getInstance();
	
}
