import React from "react";
import Lista_Medico from "../../../components/components-Lista/Lista_Medico/Lista_Medico";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Lista_Medicos() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Lista de Médicos</h4>
        <div className="">
          <Lista_Medico />
        </div>
      </div>
    </div>
  );
}
