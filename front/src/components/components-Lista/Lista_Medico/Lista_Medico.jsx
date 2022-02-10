import React, { useEffect, useState, useContext } from 'react';
import { Table, Button } from 'react-bootstrap';
import api from '../../../Services/api';
import './Styles.css';
import { Context } from '../../../Context/AuthContext'
import { useHistory } from 'react-router-dom'
export default function Lista_Medico() {

  const [medicos, setMedicos] = useState()
  const {idMedico,SetIDMedico}= useContext(Context)
  const history = useHistory()
  const token = localStorage.getItem("tokenA")

  useEffect(() => {        

    api.get("medicos?isDeleted=false", {
      headers:{"Authorization":`Bearer ${token}`}
     })
       .then((response)=>{
       
               setMedicos(response.data)
               console.log(response.data)
       })

       .catch(() => {
           console.log("Erro no get")

       })
      },[])

      console.log(medicos)
      function apagar (id){
        api.delete(`/medicos/cadastrar/${id}`,{
          headers:{"Authorization":`Bearer ${token}`}
         })
        window.location.reload()
      }

     async function setID(id){
        SetIDMedico(id)
       history.push("/edicao-medicos")
      }

      
  return (
    <div className="table-responsive-sm">
      <Table striped boerdered hover className="table-responsive-sm">
        <thead>
          <tr>
            <th>medicos</th>
            <th>Nome</th>
            <th>CRM</th>
            <th>Email</th>
            <th>Telefone</th>
          </tr>
        </thead>
        <tbody>
          {medicos?.map((medico) => {
            return (
              <tr>
                <td>{medico?.id}</td>
                <td>{medico?.nomeMedico}</td>
                <td>{medico?.crm}</td>
                <td>{medico?.emailMedico}</td>
                <td>{medico?.telefone}</td>
                <div>
                  <Button className="botao-atualizar" onClick={value=>(setID(medico?.id))}>Atualizar</Button>
                  <Button className="botao-excluir" onClick={value => (apagar(medico?.id))}>Excluir</Button>
                </div>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
}
