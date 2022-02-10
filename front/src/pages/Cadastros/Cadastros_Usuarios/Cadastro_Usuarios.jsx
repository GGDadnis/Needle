import React from "react";
import Form_Usuario from "../../../components/components-Cadastro/Form_Usuario/Form_Usuario";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Cadastro_Usuarios() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Cadastro de Usuários</h4>
        <div className="">
          <Form_Usuario />
        </div>
      </div>
    </div>
  );
}
