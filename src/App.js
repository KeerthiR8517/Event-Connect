import React, { useState } from "react";
import Login from "./components/Login";
import EventList from "./components/EventList";

function App() {
  const [token, setToken] = useState("");

  return (
    <div className="App">
      {!token ? <Login setToken={setToken} /> : <EventList token={token} />}
    </div>
  );
}

export default App;
