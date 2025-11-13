// src/App.jsx
import React from "react";
import { BrowserRouter as Router } from "react-router-dom";
import AppRoutes from "./routes";
import HeaderPrincipal from "./components/HeaderPrincipal";


function App() {
  console.log("App component rendered");
  return (
    <>
      <Router>
        <HeaderPrincipal />
        <AppRoutes />
      </Router>

      {/* Acessibilidade fora do Router, mas ainda dentro do fragment */}
     
    </>
  );
}

export default App;
