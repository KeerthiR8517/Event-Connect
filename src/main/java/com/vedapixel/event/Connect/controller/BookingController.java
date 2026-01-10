package com.vedapixel.event.Connect.controller;

import com.vedapixel.event.Connect.entity.Booking;
import com.vedapixel.event.Connect.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*")
@RequestMapping("/bookings")
@RestController

public class BookingController {
    @Autowired private BookingService bookingService;

    @PostMapping
    public Map<String,String> book(@RequestHeader("Authorization") String authHeader,
                                   @RequestBody Booking booking) {
        Map<String,String> res = new HashMap<>();
        try {
            bookingService.bookEvent(authHeader, booking);
            res.put("message","Booking successful");
        } catch(Exception e) {
            res.put("error", e.getMessage());
        }
        return res;
    }
}
