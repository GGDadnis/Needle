import React from "react";
import Edit_Medico from "../../../components/components-Edicao/Edit_Medico/Edit_Medico";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Edicao_Medicos() {

  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Atualização de Médicos</h4>
        <div className="">
          <Edit_Medico />
        </div>
      </div>
    </div>
  );
}
