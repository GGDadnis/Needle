import React from "react";
import Edit_Paciente from "../../../components/components-Edicao/Edit_Paciente/Edit_Paciente";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Edicao_Pacientes() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Atualização de Pacientes</h4>
        <div className="">
          <Edit_Paciente />
        </div>
      </div>
    </div>
  );
}
