import React, { useEffect, useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import api from '../../../Services/api';
import './Styles.css';

export default function Lista_Recibo() {
  const [recibo, setRecibos] = useState();
  const [pacientes, setPacientes] = useState(null, []);
  const [email, setEmail] = useState(null, []);
  const token = localStorage.getItem("tokenA")

  useEffect(() => {

    api.get("recibos", {
      headers: { "Authorization": `Bearer ${token}` }
    })
      .then((response) => {

        setRecibos(response.data)
        console.log(response.data)
      })


  }, [])

  async function setPacientess(response, id) {
    
    api.post(`email?id=${id}`, {

      destino: response.data.email,
      corpo: "Isso é um e-mail automático, favor não responder!",
      assunto: "Envio do recibo da Clínica Médica.",

    }, {
      headers: { "Authorization": `Bearer ${token}` }
    }

    ).then((response) => {
    })

  }

  async function setarpaciente(a) {
     setPacientes(a)

  }
  async function setarEmail(b) {
     setEmail(b)
  }

  async function pegarEmail(nome, id) {


    console.log('entrou')
    console.log("nome do paciente clicado = ", nome)
    console.log(token);

    await api.get(`pacientes/nome/${nome}`, {
      headers: { "Authorization": `Bearer ${token}` }
    })
      .then((response) => {
        //setarpaciente(response.data)
        setPacientess(response, id)
      })
  }


  return (
    <div class="table-responsive-sm">
      <Table striped boerdered hover class="table-responsive-sm">
        <thead>
          <tr>
            <th>id</th>
            <th>Nome do médico</th>
            <th>Nome do paciente</th>
            <th>Serviço</th>
            <th>Valor</th>
          </tr>
        </thead>
        <tbody>
          {recibo?.map((recibo) => {
            return (
              <tr>
                <td>{recibo?.id}</td>
                <td>{recibo?.medico}</td>
                <td>{recibo?.paciente}</td>
                <td>{recibo?.servico}</td>
                <td>{recibo?.valor}</td>
                <div>
                  <Button className="botao-atualizar" value={recibo.paciente, recibo.id} onClick={value => (pegarEmail(recibo?.paciente, recibo?.id))}>Enviar por email</Button>
                </div>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
}
