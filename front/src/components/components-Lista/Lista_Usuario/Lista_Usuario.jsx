import React, { useEffect, useState, useContext } from 'react';
import { Table, Button } from 'react-bootstrap';
import api from '../../../Services/api';
import './Styles.css';
import { Context } from '../../../Context/AuthContext'
import { useHistory } from 'react-router-dom'



export default function Lista_Usuario() {
  const [usuario, setUsuario] = useState();
  const {idUsuario,SetIdUsuario} = useContext(Context)
  const history = useHistory()
  const token = localStorage.getItem("tokenA")

  useEffect(() => {        
   
    api.get("cadastro/usuarios", {
      headers:{"Authorization":`Bearer ${token}`}
     })
       .then((response)=>{
               setUsuario(response.data)
               console.log(response.data)  
       })
       
      //  .catch((response) => {
      //      console.log()
      //  })
      },[])

      async function setID(id){
        SetIdUsuario(id)
       history.push("/edicao-usuarios")
      }
     
      function apagar (id){
        api.delete(`/cadastro/usuarios/${id}`, {
          headers:{"Authorization":`Bearer ${token}`}
         })
        window.location.reload()
      } 
     
      if(localStorage.getItem("auto") == false){
        return(
          <h1>PODE NAUM</h1>
        )
      }
    
  return (
    <div class="table-responsive-sm">
      <Table striped boerdered hover class="table-responsive-sm">
       
        {!usuario?(
          <h2 >acesso negado</h2>
        ):(
          <>

          <thead>
          <tr>
            <th>id</th>
            <th>Nome</th>
            <th>Email</th>
          </tr>
        </thead>

           <tbody>
          {usuario?.map((usuario) => {
            return (
              <tr>
                <td>{usuario?.id}</td>
                <td>{usuario?.nome}</td>
                <td>{usuario?.email}</td>
              
              <div>
                  <Button className="botao-atualizar" onClick={value=>(setID(usuario?.id))}>Atualizar</Button>
                  <Button className="botao-excluir" onClick={value => (apagar(usuario?.id))}>Excluir</Button>
              </div>
              </tr>
            );
          })}
        </tbody>
         </>
        )}
       
      </Table>
    </div>
  );
}
