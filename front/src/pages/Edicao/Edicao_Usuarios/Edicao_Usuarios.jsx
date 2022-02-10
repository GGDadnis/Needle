import React from "react";
import Edit_Usuario from "../../../components/components-Edicao/Edit_Usuario/Edit_Usuario";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Edicao_Usuarios() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Atualização de Usuários</h4>
        <div className="">
          <Edit_Usuario />
        </div>
      </div>
    </div>
  );
}
