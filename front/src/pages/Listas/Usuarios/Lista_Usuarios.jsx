import React from "react";
import Lista_Usuario from "../../../components/components-Lista/Lista_Usuario/Lista_Usuario";
import NavBar from "../../../components/NavBar";
import "./Styles.css";

export default function Lista_Usuarios() {
  let h4stilo = {
    marginTop: "30px",
    textAlign: "center",
  };
  return (
    <div>
      <NavBar />
      <div className="container">
        <h4 style={h4stilo}>Lista de Usuários</h4>
        <div className="">
          <Lista_Usuario />
        </div>
      </div>
    </div>
  );
}
