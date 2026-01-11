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
- Tested APIs using **Postman**

### Database (PostgreSQL)
- Tables: `users`, `events`, `bookings`
- Queries optimized with indexes

 ## System Design

Below is a architecture of the Event Connect system:
    
<img width="463" height="540" alt="image" src="https://github.com/user-attachments/assets/0ae06ddb-97b1-4bca-b7e0-e5a71174a8fd" />


# Setup Instructions

## Backend (Spring Boot)

- Install Java 8 and Maven.
- Clone repo and navigate to backend folder:
- git clone <your-repo-url>
- IntelliJ → Open → select backend folder
- Open as Maven project When IntelliJ opens: Click “Load Maven Project” and Wait until dependencies download
- check pom.xml
- Set Java SDK in IntelliJ: File → Project Structure
- Project SDK → select JDK 8 / 17
- Project language level → same as JDK - Apply → OK
- Configure database(application.properties)
- Run Spring Boot application
- Test API (postman)

## Frontend (React)

- Download Node.js from: https://nodejs.org
- After install, check in terminal:
- node -v
- npm -v
- Clone the frontend project
- after clone : File → Open Folder → select frontend project folder
- Open terminal in VS Code
- cd event-connect-frontend
- Install dependencies(npm install, npm install axios)
- Run the frontend - npm start
- After running, terminal will show something like:
- Local: http://localhost:3000

## Database

- Install PostgreSQL.
- Set password and Port: 5432 (default)
- Open pgAdmin
- Create Database and schema(event)
- CREATE TABLE event.users (...);
- CREATE TABLE event.events (...);
- CREATE TABLE event.bookings (...);

### Note:
Make sure backend is running before frontend to get JWT authentication working.

## Screenshots
### Login Page
<img width="1919" height="956" alt="image" src="https://github.com/user-attachments/assets/88814ef0-6557-43cd-9c3b-4df8fd38380a" />

### Event List
<img width="1917" height="795" alt="image" src="https://github.com/user-attachments/assets/16817ba0-469d-45fc-955b-05a85f68f52e" />

### Booking & Top Most Booked Events
<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/dbb79e5c-e3e7-46d6-acd5-06d13c965e8d" />




