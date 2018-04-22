package com.jpa.services;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.EventDao;
import com.jpa.dao.EventProviderDao;
import com.jpa.models.Event;
import com.jpa.models.EventProvider;

@CrossOrigin
@RestController
public class EventService {

	EventDao evDao = EventDao.getInstance();
	
	@RequestMapping(value="api/event", method=RequestMethod.GET)
	public List<Event> getEventFromInfo(
			@RequestParam(value="name",required=false)String name)
	{
			if(name!=null) {
				return evDao.findAllEventsByName(name);
			}
			else {
				return evDao.findAllEvents();
			}
	}

	@RequestMapping(value= "api/event/{Id}", method = RequestMethod.GET)
	public Event findEventById(@PathVariable(name="Id") int Id) {
		return evDao.findEventById(Id);
	}
	
	@RequestMapping(value= "api/event/{Id}/eventprovider", method = RequestMethod.GET)
	public EventProvider findProviderByEventId(@PathVariable(name="Id") int Id) {
		EventProviderDao eDao = EventProviderDao.getInstance();
		int provider_id = eDao.findEventProviderByEventId(Id);
		int person_id = eDao.findPersonIdByProviderId(provider_id);
		return eDao.findEventProviderById(person_id);
	}
}
