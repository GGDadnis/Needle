import React, { createContext, useState, useEffect } from 'react'; 
import axios from 'axios';
import api from '../Services/api'
import history from '../Services/history'


const Context = createContext();

function AuthProvider({ children }){

    const [loading, setLoagind] = useState(true);
    const [idMedico,SetIDMedico]= useState()
    const [idPaciente,SetIDPaciente]= useState()
    const [idUsuario,SetIdUsuario]= useState()
    const [token, setToken] = useState({
        tokenAcesso:"",
        tokenRefresh: ""
    });
    const [auth, setAuth] = useState(false);

    return(
        <Context.Provider value={{idUsuario,SetIdUsuario,token,setToken,auth,setAuth,idMedico,SetIDMedico,idPaciente,SetIDPaciente}}>
            {children}
        </Context.Provider>
    );

}

export { Context, AuthProvider }