
import { Route, Switch } from "react-router-dom";
import Home from "../pages/Home/Home";
import Login from "../pages/Login/Login";
import React, { useContext, useEffect } from "react";
import Cadastro_Medicos from "../pages/Cadastros/Cadastros_Medicos/Cadastro_Medicos";
import Cadastro_Pacientes from "../pages/Cadastros/Cadastros_Pacientes/Cadastro_Pacientes";
import Cadastro_Usuarios from "../pages/Cadastros/Cadastros_Usuarios/Cadastro_Usuarios";
import Cadastro_Recibos from "../pages/Cadastros/Cadastros_Recibos/Cadastro_Recibos";
import Edicao_Medicos from "../pages/Edicao/Edicao_Medicos/Edicao_Medicos";
import Edicao_Pacientes from "../pages/Edicao/Edicao_Pacientes/Edicao_Pacientes";
import Edicao_Usuarios from "../pages/Edicao/Edicao_Usuarios/Edicao_Usuarios";
import Lista_Medicos from "../pages/Listas/Medicos/Lista_Medicos";
import Lista_Usuarios from "../pages/Listas/Usuarios/Lista_Usuarios";
import Lista_Recibos from "../pages/Listas/Recibos/Lista_Recibos";
import Lista_Pacientes from "../pages/Listas/Pacientes/Lista_Pacientes";
import { Context } from "../Context/AuthContext";
import { Redirect } from "react-router-dom/cjs/react-router-dom.min";


function CustomRoute({ isPrivate, ...rest }) {
  // if (isPrivate && !authenticated){
  //     // return <Redirect to='/login' />
  // }
  // return <Route {...rest} />;
}

export default function Routes() {
 const authenticated = localStorage.getItem("auth");
  useEffect(() => {
    console.log("veio daqui", authenticated);
  }, [authenticated]);

  return (

    <Switch>
      <Route exact path="/login" component={Login} />
      <Route exact path="/" component={Home} />
      <Route exact path="/cadastro-medicos" component={Cadastro_Medicos} />
      <Route exact path="/cadastro-pacientes" component={Cadastro_Pacientes} />
      <Route exact path="/cadastro-usuarios" component={Cadastro_Usuarios} />
      <Route exact path="/cadastro-recibos" component={Cadastro_Recibos} />
      <Route exact path="/edicao-medicos" component={Edicao_Medicos} />
      <Route exact path="/edicao-pacientes" component={Edicao_Pacientes} />
      <Route exact path="/edicao-usuarios" component={Edicao_Usuarios} />
      <Route exact path="/lista-medicos" component={Lista_Medicos}/>
      <Route exact path="/lista-usuarios" component={Lista_Usuarios}/>
    <Route exact path="/lista-pacientes" component={Lista_Pacientes}/>
    <Route exact path="/lista-recibos" component={Lista_Recibos}/>
      <Route exact path="*" component={Home} />
    </Switch>
  );
}