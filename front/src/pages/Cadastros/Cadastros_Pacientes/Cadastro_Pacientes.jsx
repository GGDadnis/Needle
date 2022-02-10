import React from "react";
import Form_Paciente from "../../../components/components-Cadastro/Form_Paciente/Form_Paciente";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Cadastro_Pacientes() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Cadastro de Pacientes</h4>
        <div className="">
          <Form_Paciente />
        </div>
      </div>
    </div>
  );
}
