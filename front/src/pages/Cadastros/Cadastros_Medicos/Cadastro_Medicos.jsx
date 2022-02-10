import React from "react";
import Form_Medico from "../../../components/components-Cadastro/Form_Medico/Form_Medico";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Cadastro_Medicos() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Cadastro de Médicos</h4>
        <div className="">
          <Form_Medico />
        </div>
      </div>
    </div>
  );
}
