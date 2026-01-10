package com.vedapixel.event.Connect.dao;

import com.vedapixel.event.Connect.entity.Booking;
import com.vedapixel.event.Connect.interfaces.BookingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDaoImpl implements BookingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createBooking(Booking booking) {
        String sql = "INSERT INTO event.bookings (event_id, username, tickets) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, booking.getEventId(), booking.getUsername(), booking.getTickets());
    }
}



