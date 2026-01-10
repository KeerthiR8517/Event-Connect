import React, { useState } from "react";
import { login } from "../api";
import "./Login.css";


export default function Login({ setToken }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await login(username, password);
      setToken(res.data.token);
      setError("");
    } catch (err) {
      setError("Invalid credentials");
    }
  };
  

  return (

    <div className="login-container">
      <h2 className="login-title">Login</h2>

      <form className="login-form" onSubmit={handleSubmit}>

         <label htmlFor="username" className="login-label">
          Username
        </label>
        <input
          className="login-input"
          placeholder="Enter Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />

        <label htmlFor="password" className="login-label">
          Password
        </label>

        <input
          className="login-input"
          placeholder="Enter Password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button className="login-button" type="submit">
          Login
        </button>
      </form>

      {error && <p className="login-error">{error}</p>}
    </div>
  );
}
