import axios from "axios";

const API_URL = "http://localhost:8080";

export const login = (username, password) =>
  axios.post(`${API_URL}/auth/login`, { username, password });

export const getEvents = () =>
  axios.get(`${API_URL}/events`);

export const bookEvent = (token, bookingData) =>
  axios.post(`${API_URL}/bookings`, bookingData, {
    headers: { Authorization: `Bearer ${token}` },
  });

  
export const getTopBookedEvents = () =>
  axios.get(`${API_URL}/events/top-booked`);