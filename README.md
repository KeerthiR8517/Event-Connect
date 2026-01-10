# Event Connect – Event Booking Platform

A simplified event booking system built with **Spring Boot** (backend) and **React** (frontend).  
Users can browse events, book tickets, and manage bookings. Includes **JWT authentication** and **rate-limiting** for bookings.

## Features

### Frontend (React)
- User login with **JWT-based authentication**
- Browse events (Event List)
- Book tickets 
- Display booking success or **rate-limit messages** (max 5 bookings/minute)
- Responsive design

### Backend (Spring Boot)
- REST APIs for authentication and booking
- JWT authentication with token expiration (15 minutes)
- Rate-limiting: Max 5 bookings per user per minute
- Proper error handling for invalid credentials, rate-limit, and server issues
- CORS enabled for frontend communication

### Database (PostgreSQL)
- Tables: `users`, `events`, `bookings`
- Queries optimized with indexes

- ## System Design

Below is a architecture of the Event Connect system:
    
<img width="463" height="540" alt="image" src="https://github.com/user-attachments/assets/0ae06ddb-97b1-4bca-b7e0-e5a71174a8fd" />


## 2. Setup Instructions

# Backend (Spring Boot)

Install Java 8 and Maven.
Clone repo and navigate to backend folder:
git clone <your-repo-url>
cd backend
Update application.properties with your PostgreSQL credentials.
Run the backend

# Frontend (React)

Navigate to frontend folder:
cd frontend
Install dependencies
npm install
Start the frontend:
npm start
Runs on http://localhost:3000

# Database

Install PostgreSQL.
Create database (e.g., event_connect) and run schema SQL script:
CREATE TABLE users (...);
CREATE TABLE events (...);
CREATE TABLE bookings (...);

# Note:
Make sure backend is running before frontend to get JWT authentication working.

## Screenshots
### Login Page
<img width="1919" height="956" alt="image" src="https://github.com/user-attachments/assets/88814ef0-6557-43cd-9c3b-4df8fd38380a" />

### Event List
<img width="1917" height="795" alt="image" src="https://github.com/user-attachments/assets/16817ba0-469d-45fc-955b-05a85f68f52e" />

### Booking & Top Most Booked Events
<img width="1726" height="954" alt="image" src="https://github.com/user-attachments/assets/433799b4-6448-486a-b9fe-090475576acd" />



