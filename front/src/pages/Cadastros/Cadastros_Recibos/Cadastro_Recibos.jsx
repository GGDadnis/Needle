import React from "react";
import Form_Recibo from "../../../components/components-Cadastro/Form_Recibo/Form_Recibo";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Cadastro_Recibos() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Cadastro de Recibos</h4>
        <div className="">
          <Form_Recibo />
        </div>
      </div>
    </div>
  );
}
