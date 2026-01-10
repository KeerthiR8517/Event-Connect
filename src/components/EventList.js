import React, { useEffect, useState } from "react";
import { getEvents, getTopBookedEvents, bookEvent } from "../api";
import "./Booking.css";

export default function EventList({ token }) {
  const [events, setEvents] = useState([]);
  const [tickets, setTickets] = useState({});
  const [messages, setMessages] = useState({});
  const [topBooked, setTopBooked] = useState([]);

  useEffect(() => {
    fetchEvents();
    fetchTopBooked();
  }, []);

  const fetchEvents = async () => {
    try {
      const res = await getEvents();
      setEvents(res.data);
    } catch (err) {
      console.error("Failed to fetch events", err);
    }
  };

  const fetchTopBooked = async () => {
    try {
      const res = await getTopBookedEvents();
      setTopBooked(res.data);
    } catch (err) {
      console.error("Failed to fetch top booked events", err);
    }
  };

  const handleBooking = async (eventId) => {
    if (!tickets[eventId] || tickets[eventId] < 1) {
      setMessages((prev) => ({ ...prev, [eventId]: "Enter at least 1 ticket" }));
      return;
    }

    const event = events.find((e) => e.id === eventId);
    if (!event) return;

    if (tickets[eventId] > event.availableTickets) {
      setMessages((prev) => ({
        ...prev,
        [eventId]: `Cannot book more than ${event.availableTickets} tickets`,
      }));
      return;
    }

    try {
      const res = await bookEvent(token, {
        eventId,
        tickets: parseInt(tickets[eventId]),
      });

      const msg = res.data.message || res.data.error || "Booking successful";
      setMessages((prev) => ({ ...prev, [eventId]: msg }));
      setTickets((prev) => ({ ...prev, [eventId]: "" }));
      fetchEvents();
      fetchTopBooked(); // update top booked after booking
    } catch (err) {
      const errMsg = err.response?.data?.error || "Booking failed";
      setMessages((prev) => ({ ...prev, [eventId]: errMsg }));
      if (errMsg.toLowerCase().includes("rate limit")) alert(errMsg);
    }
  };

  return (
    <div>
      {/* Main Events */}
      <h2>🎫 Available Events</h2>
      <div className="top-booked-cards">
        {events.map((e) => (
          <div key={e.id} className="top-booked-card">
            <b>{e.name}</b>
            <p>{e.location}</p>
            <p>{e.availableTickets} tickets left</p>
            <input
              type="number"
              min="1"
              placeholder="Select ticket quantity"
              value={tickets[e.id] || ""}
              onChange={(ev) => setTickets({ ...tickets, [e.id]: ev.target.value })}
            />
            <button onClick={() => handleBooking(e.id)}>Book</button>
            {messages[e.id] && (
              <p
                className={`message ${
                  messages[e.id].toLowerCase().includes("successful")
                    ? "success"
                    : "error"
                }`}
              >
                {messages[e.id]}
              </p>
            )}
          </div>
        ))}
      </div>

      {/* Top Booked Events */}
      {topBooked.length > 0 && (
        <div className="top-booked-container">
          <h3>🔥 Most Booked Events</h3>
          <div className="top-booked-cards">
            {topBooked.map((e, index) => (
              <div key={e.id} className="top-booked-card">
                {index === 0 && <span>🏆 Top</span>}
                <b>{e.name}</b>
                <p>{e.location}</p>
                <p>{e.availableTickets} tickets left</p>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}
