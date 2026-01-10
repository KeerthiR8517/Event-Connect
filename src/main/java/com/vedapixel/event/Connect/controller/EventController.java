package com.vedapixel.event.Connect.controller;

import com.vedapixel.event.Connect.entity.Event;
import com.vedapixel.event.Connect.interfaces.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin("*")
public class EventController {
    @Autowired
    private EventDao eventDao;

    @GetMapping
    public List<Event> getAllEvents() { return eventDao.getAllEvents(); }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) { return eventDao.getEventById(id); }

    @GetMapping("/top-booked")
    public List<Event> getTopBookedEvents() {
        return eventDao.getTop3MostBookedEvents();
    }

}
