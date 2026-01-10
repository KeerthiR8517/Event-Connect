import React, { useState } from "react";
import axios from "axios";

function Booking() {
  const [eventName, setEventName] = useState("");
  const [tickets, setTickets] = useState(1);
  const [message, setMessage] = useState("");

  const handleBooking = async (e) => {
    e.preventDefault();
    setMessage("");

    const token = localStorage.getItem("token");

    try {
      const res = await axios.post("http://localhost:8080/bookings",
        { eventName, tickets },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setMessage(res.data.message);
    } catch (err) {
      setMessage(err.response?.data?.error || "Booking failed");
    }
  };

  return (
    <div>
      <h2>Book Event</h2>
      <form onSubmit={handleBooking}>
        <input
          type="text"
          placeholder="Event Name"
          value={eventName}
          onChange={e => setEventName(e.target.value)}
          required
        />
        <br/>
        <input
          type="number"
          placeholder="Tickets"
          value={tickets}
          min="0"
          onChange={e => setTickets(e.target.value)}
          required
        />
        <br/>
        <button type="submit">Book</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default Booking;
