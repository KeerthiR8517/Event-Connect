package com.vedapixel.event.Connect.interfaces;

import com.vedapixel.event.Connect.entity.Booking;

public interface BookingService {
    void bookEvent(String token, Booking booking);
}
