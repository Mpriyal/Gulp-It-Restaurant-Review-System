package com.jpa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.EventDao;
import com.jpa.dao.EventProviderDao;
import com.jpa.dao.MenuDao;
import com.jpa.models.Event;
import com.jpa.models.EventProvider;
import com.jpa.models.Menu;
import com.jpa.models.Restaurant;
import com.jpa.models.RestaurantOwner;

@CrossOrigin
@RestController
public class EventProviderService {
	
	EventProviderDao eDao = EventProviderDao.getInstance();
	
	@RequestMapping(value="api/eventprovider", method=RequestMethod.POST)
	public void addNewEventProvider(@RequestBody EventProvider eventProvider) {
		
		eDao.createEventProvider(eventProvider);
	}
	
	@RequestMapping(value="api/eventprovider/{providerId}", method=RequestMethod.GET)
	public EventProvider findProviderById(@PathVariable(name="providerId") int providerId) {
		return eDao.findEventProviderById(providerId);
	}
	
	@RequestMapping(value="api/eventprovider", method=RequestMethod.GET)
	public List<EventProvider> getProviderByCredentials(@RequestParam(value="username",required=false)String username,
			@RequestParam(value="password",required=false)String password) {
			if(username!=null&&password!=null) {
				List<EventProvider> provider_list1 = new ArrayList<>();
				EventProvider provider1 = eDao.findEventProviderByCredentials(username, password);
				provider_list1.add(provider1);
				return provider_list1;
			}
			else if(username!=null) {
				List<EventProvider> provider_list = new ArrayList<>();
				EventProvider provider = eDao.findEventProviderByUsername(username);
				provider_list.add(provider);
				return provider_list;
			}
			else if(password!=null) {
				List<EventProvider> provider_list3 = new ArrayList<>();
				return provider_list3;
			}
			else {
				return eDao.findAllEventProviders();
			}
	}
	
	@RequestMapping(value="api/eventprovider/{providerId}", method=RequestMethod.PUT)
	public void updateProvider(@PathVariable(name="providerId")int id,@RequestBody EventProvider newProvider) {
		  eDao.updateProvider(id, newProvider);
	}
	

	@RequestMapping(value="api/eventprovider/{providerId}", method=RequestMethod.DELETE)
	public int deleteProviderById(@PathVariable (name="providerId") int providerId) {
		int result=eDao.deleteProvider(providerId);
		return result;
	}
	
	@RequestMapping(value="api/eventprovider/{providerId}/event", method=RequestMethod.GET)
	public List<Event> getAllEventsOfProvider(@PathVariable(name="providerId")int providerId,
			@RequestParam(value="name",required=false)String name) {
		
		EventDao evDao = EventDao.getInstance();
		int Provider_id1 = 0;
		
		if(name!=null) {
			return evDao.findAllEventsByName(name);
		}
		else
			Provider_id1 = eDao.findProvIdByPersonId(providerId);
			return evDao.findAllEventByProvider(Provider_id1);
	}
	
	@RequestMapping(value="api/eventprovider/{providerId}/event/{eventId}", method=RequestMethod.GET)
	public Event findEventofProviderbyId(@PathVariable (name="providerId") int providerId,@PathVariable (name="eventId") int eventId) {
		EventDao evDao = EventDao.getInstance();
		int Provider_id = eDao.findProvIdByPersonId(providerId);
		Event result=evDao.findEventByProviderId(eventId, Provider_id);
		return result;
	}
	

	@RequestMapping(value="api/eventprovider/{providerId}/event", method=RequestMethod.POST)
	public void addEventForProvider(@RequestBody Event event,@PathVariable (name="providerId") int providerId) {
		EventDao evDao = EventDao.getInstance();
		int Provider_id = eDao.findProvIdByPersonId(providerId);
		evDao.addEventForProvider(event, Provider_id);
	}
	
	//to update a restaurant, with id as restaurantId, by owner, with id ownerId
	@RequestMapping(value="api/eventprovider/{providerId}/event/{eventId}", method=RequestMethod.PUT)
	public void updateEventofProvider(@RequestBody Event newEvent,@PathVariable (name="providerId") int providerId,@PathVariable (name="eventId") int eventId) {
		EventDao evDao = EventDao.getInstance();
		int Provider_id = eDao.findProvIdByPersonId(providerId);
		evDao.updateEvent(Provider_id, newEvent, eventId);
	}
	
	//to delete a restaurant, with id as restaurantId, by owner, with id ownerId
	@RequestMapping(value="api/eventprovider/{providerId}/event/{eventId}", method=RequestMethod.DELETE)
	public void deleteEventofProvider(@PathVariable (name="providerId") int providerId,@PathVariable (name="eventId") int eventId) {
		EventDao evDao = EventDao.getInstance();
		int Provider_id = eDao.findProvIdByPersonId(providerId);
		evDao.deleteEvent(eventId, Provider_id);
	}



}
