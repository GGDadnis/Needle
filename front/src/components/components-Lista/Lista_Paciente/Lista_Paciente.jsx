import { Table, Button } from 'react-bootstrap';
import api from '../../../Services/api';
import './Styles.css';
import { Context } from '../../../Context/AuthContext'
import { useHistory } from 'react-router-dom'
import React, { useEffect, useState, useContext } from 'react';

export default function Lista_Paciente() {
  const [pacientes, setPacientes] = useState();
  const {idPaciente,SetIDPaciente}= useContext(Context)
  const history = useHistory()
  const token = localStorage.getItem("tokenA")
  
  useEffect(() => {        
    
    api.get("pacientes?isDeleted=false", {
      headers:{"Authorization":`Bearer ${token}`}
     })
       .then((response)=>{
       
               setPacientes(response.data)
               console.log(response.data)
       })

       .catch(() => {
           console.log("Erro no get")

       })
      },[])
      function apagar (id){
        api.delete(`/pacientes/${id}`,{
          headers:{"Authorization":`Bearer ${token}`}
         })
        window.location.reload()
      }
      async function setID(id){
        SetIDPaciente(id)
       history.push("/edicao-pacientes")
      }

  return (
    <div class="table-responsive-sm">
      <Table striped boerdered hover class="table-responsive-sm">
        <thead>
          <tr>
            <th>id</th>
            <th>Nome</th>
            <th>CPF</th>
            <th>Email</th>
            <th>Telefone</th>
          </tr>
        </thead>
        <tbody>
          {pacientes?.map((paciente) => {
            return (
              <tr>
                <td>{paciente?.id}</td>
                <td>{paciente?.nomePaciente}</td>
                <td>{paciente?.cpf}</td>
                <td>{paciente?.email}</td>
                <td>{paciente?.telefone}</td>
                <div>
                  <Button className="botao-atualizar" onClick={value=>(setID(paciente?.id))}>Atualizar</Button>
                  <Button className="botao-excluir" value="Refresh Page" onClick={value => (apagar(paciente?.id))}>Excluir</Button>
                </div>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
}
