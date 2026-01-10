package com.vedapixel.event.Connect.service;

import com.vedapixel.event.Connect.Security.JwtUtil;
import com.vedapixel.event.Connect.entity.Booking;
import com.vedapixel.event.Connect.entity.Event;
import com.vedapixel.event.Connect.interfaces.BookingDao;
import com.vedapixel.event.Connect.interfaces.BookingService;
import com.vedapixel.event.Connect.interfaces.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired private JwtUtil jwtUtil;
    @Autowired private BookingDao bookingDao;
    @Autowired private EventDao eventDao;

    private Map<String, List<Long>> userBookingTimestamps = new HashMap<>();
    private final int MAX_BOOKINGS = 5;
    private final long TIME_WINDOW = 60*1000;

    @Override
    public void bookEvent(String token, Booking booking) {
        if (token.startsWith("Bearer ")) token = token.substring(7);
        String username;
        try { username = jwtUtil.extractUsername(token); }
        catch (Exception e) { throw new RuntimeException("Invalid or expired token"); }

        // Rate limiting
        long now = System.currentTimeMillis();
        userBookingTimestamps.putIfAbsent(username, new ArrayList<>());
        List<Long> ts = userBookingTimestamps.get(username);
        ts.removeIf(t -> t + TIME_WINDOW < now);
        if (ts.size() >= MAX_BOOKINGS) throw new RuntimeException("Rate limit exceeded");
        ts.add(now);
        userBookingTimestamps.put(username, ts);

        // Check event
        Event event = eventDao.getEventById(booking.getEventId());
        if (event == null) throw new RuntimeException("Event not found");
        if (event.getAvailableTickets() < booking.getTickets())
            throw new RuntimeException("Not enough tickets");

        booking.setUsername(username);
        bookingDao.createBooking(booking);
        eventDao.reduceAvailableTickets(booking.getEventId(), booking.getTickets());
    }
}
