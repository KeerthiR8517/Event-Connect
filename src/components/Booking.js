import React, { useState } from "react";
import { bookEvent } from "../api";
import "./Booking.css";

export default function Booking({ token, event, onBookingSuccess }) {
  const [tickets, setTickets] = useState(1);
  const [message, setMessage] = useState("");

  const handleBooking = async () => {
    if (tickets < 1) {
      setMessage("Please enter at least 1 ticket");
      return;
    }
    if (tickets > event.availableTickets) {
      setMessage(`Cannot book more than ${event.availableTickets} tickets`);
      return;
    }

    try {
      const res = await bookEvent(token, {
        eventId: event.id,
        tickets: parseInt(tickets),
      });

      const msg = res.data.message || res.data.error || "Booking successful";
      setMessage(msg);
      onBookingSuccess();
      setTickets(1);
    } catch (err) {
      const errMsg = err.response?.data?.error || "Booking failed";
      setMessage(errMsg);
      if (errMsg.toLowerCase().includes("rate limit")) alert(errMsg);
    }
  };

  return (
    <div className="event-card">
      <h3>{event.name}</h3>
      <p>{event.description}</p>
      <p>
        Location: {event.location} | Date: {event.eventDate}
      </p>
      <p>Available Tickets: {event.availableTickets}</p>

      <div>
        <input
          type="number"
          min="1"
          value={tickets}
          onChange={(e) => setTickets(Number(e.target.value))}
        />
        <button onClick={handleBooking} disabled={event.availableTickets === 0}>
          Book
        </button>
      </div>

      {message && (
        <p className={`message ${message.toLowerCase().includes("successful") ? "success" : "error"}`}>
          {message}
        </p>
      )}
    </div>
  );
}
