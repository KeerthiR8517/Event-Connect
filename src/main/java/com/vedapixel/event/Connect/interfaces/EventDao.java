package com.vedapixel.event.Connect.interfaces;

import com.vedapixel.event.Connect.entity.Event;

import java.util.List;

public interface EventDao {

    // Get list of all events
    List<Event> getAllEvents();

    // Get single event by ID
    Event getEventById(Long id);

    // Reduce available tickets for an event after booking
    void reduceAvailableTickets(Long eventId, int tickets);

    List<Event> getTop3MostBookedEvents();

}
