import React from "react";
import Lista_Recibo from "../../../components/components-Lista/Lista_Recibo/Lista_Recibo";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Lista_Recibos() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Lista de Recibos</h4>
        <div className="">
          <Lista_Recibo />
        </div>
      </div>
    </div>
  );
}
