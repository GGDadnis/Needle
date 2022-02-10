import React from "react";
import Lista_Paciente from "../../../components/components-Lista/Lista_Paciente/Lista_Paciente";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Lista_Pacientes() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Lista de Pacientes</h4>
        <div className="">
          <Lista_Paciente />
        </div>
      </div>
    </div>
  );
}
