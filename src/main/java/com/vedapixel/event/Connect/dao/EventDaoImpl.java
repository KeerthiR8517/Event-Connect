package com.vedapixel.event.Connect.dao;

import com.vedapixel.event.Connect.entity.Event;
import com.vedapixel.event.Connect.interfaces.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EventDaoImpl implements EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Event> getAllEvents() {
        String sql = "SELECT * FROM event.events";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Event e = new Event();
            e.setId(rs.getLong("id"));
            e.setName(rs.getString("name"));
            e.setDescription(rs.getString("description"));
            e.setLocation(rs.getString("location"));
            e.setEventDate(rs.getString("event_date"));
            e.setTotalTickets(rs.getInt("total_tickets"));
            e.setAvailableTickets(rs.getInt("available_tickets"));
            return e;
        });
    }

    @Override
    public Event getEventById(Long id) {
        String sql = "SELECT * FROM event.events WHERE id=?";
        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
            if (rs.next()) {
                Event e = new Event();
                e.setId(rs.getLong("id"));
                e.setName(rs.getString("name"));
                e.setDescription(rs.getString("description"));
                e.setLocation(rs.getString("location"));
                e.setEventDate(rs.getString("event_date"));
                e.setTotalTickets(rs.getInt("total_tickets"));
                e.setAvailableTickets(rs.getInt("available_tickets"));
                return e;
            }
            return null;
        });
    }

    @Override
    public void reduceAvailableTickets(Long eventId, int tickets) {
        String sql = "UPDATE events SET available_tickets = available_tickets - ? WHERE id=?";
        jdbcTemplate.update(sql, tickets, eventId);
    }

    public List<Event> getTop3MostBookedEvents() {
        String sql = """
        SELECT e.*
        FROM events e
        JOIN bookings b ON e.id = b.event_id
        GROUP BY e.id
        ORDER BY COUNT(b.id) DESC
        LIMIT 3
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Event e = new Event();
            e.setId(rs.getLong("id"));
            e.setName(rs.getString("name"));
            e.setDescription(rs.getString("description"));
            e.setLocation(rs.getString("location"));
            e.setEventDate(rs.getString("event_date"));
            e.setTotalTickets(rs.getInt("total_tickets"));
            e.setAvailableTickets(rs.getInt("available_tickets"));
            return e;
        });
    }



}
