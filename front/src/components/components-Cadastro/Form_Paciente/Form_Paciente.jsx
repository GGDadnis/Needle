import { Form, Row, Col, Button } from "react-bootstrap";
import "./Styles.css";
import api from '../../../Services/api'
import React, { useState } from "react";
import axios from "axios";


export default function Form_Paciente() {

  const token = localStorage.getItem("tokenA")
  
  const[form, setForm] = useState({
    nomePaciente:"",
    email:"",
    telefone:"",
    cpf:"",
    rg:"",
    nascimentoPaciente:"",
    maePaciente:"",
    numero:"",
    complemento:"",
    cep:"",
    cidade:"",
    uf:"",
    logradouro:"",
    bairro:""})

  function CadastrarPacientes(){
    api.post("pacientes",{
              
          nomePaciente:form.nomePaciente,
          email:form.email,
          telefone:form.telefone,
          cpf:form.cpf,
          rg:form.rg,
          nascimentoPaciente:form.nascimentoPaciente,
          maePaciente:form.maePaciente,
          numero:form.numero,
          complemento:form.complemento,
          endereco:{
              cep:form.cep
       }},{
        headers:{"Authorization":`Bearer ${token}`}
      }
              
            
      ).then(response => console.log(response)).catch((erro)=>console.log(erro))
      window.location.reload()
  }

  function ValidarCep(){

    axios.get("https://viacep.com.br/ws/" + form.cep + "/json/")
    .then(response=>{
      
      setForm({...form,cidade:response.data.localidade,uf:response.data.uf,logradouro:response.data.logradouro,bairro:response.data.bairro})
    })
  }

  function changeForm(e){

    const {name, value} = e.target;
    console.log(form) 
    setForm({...form, [name]: value})
    console.log(form)

  }


  return (
    <div className="">
      <Row>
        <Col>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicNome">
                <Form.Label>
                  <h6>Nome</h6>
                </Form.Label>
                <Form.Control type="name"name="nomePaciente" value={form.nomePaciente} onChange={changeForm} placeholder="Digite o nome" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Row>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicCPF">
                <Form.Label>
                  <h6>CPF</h6>
                </Form.Label>
                <Form.Control type="name" name="cpf" value={form.cpf} onChange={changeForm} placeholder="Digite o CPF" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicRG">
                <Form.Label>
                  <h6>RG</h6>
                </Form.Label>
                <Form.Control type="name" name="rg" onChange={changeForm} value={form.rg}placeholder="Digite o RG" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Row>
        <Col>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicMae">
                <Form.Label>
                  <h6>Nome da mãe</h6>
                </Form.Label>
                <Form.Control type="name" name="maePaciente" onChange={changeForm} value={form.maePaciente}placeholder="Digite o nome da mãe" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Row>
      <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicNascimento">
                <Form.Label>
                  <h6>Data de nascimento</h6>
                </Form.Label>
                <Form.Control type="name"name="nascimentoPaciente"onChange={changeForm}  value={form.nascimentoPaciente} placeholder="Data de nascimento" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        </Row>
      <Row>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicEmail">
                <Form.Label>
                  <h6>Email</h6>
                </Form.Label>
                <Form.Control type="name"name="email" value={form.email} onChange={changeForm} placeholder="Digite o email" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicTelefone">
                <Form.Label>
                  <h6>Telefone</h6>
                </Form.Label>
                <Form.Control type="name" name="telefone"onChange={changeForm}  value={form.telefone}placeholder="Digite o telefone" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Row>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicEmail">
                <Form.Label>
                  <h6>CEP</h6>
                </Form.Label>
                <Form.Control type="name" name="cep" value={form.cep} onChange={changeForm} placeholder="Digite o CEP" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        <Col className="col-cep">
          <Button className="botao-cep" onClick={ValidarCep}>
            <h6>Validar CEP</h6>
          </Button>
        </Col>
      </Row>
      <Row>
        <Col sm={9}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicCidade">
                <Form.Label>
                  <h6>Cidade</h6>
                </Form.Label>
                <Form.Control type="name"  value={form.cidade} placeholder="Cidade" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        <Col sm={3}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicUF">
                <Form.Label>
                  <h6>UF</h6>
                </Form.Label>
                <Form.Control type="name"  value={form.uf}placeholder="UF" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Row>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicLogradouro">
                <Form.Label>
                  <h6>Logradouro</h6>
                </Form.Label>
                <Form.Control type="name" value={form.logradouro} placeholder="Logradouro" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicBairro">
                <Form.Label>
                  <h6>Bairro</h6>
                </Form.Label>
                <Form.Control type="name"  value={form.bairro}placeholder="Bairro" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Row>
        <Col sm={3}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicNumero">
                <Form.Label>
                  <h6>Número</h6>
                </Form.Label>
                <Form.Control type="name" onChange={changeForm}  name="numero" value={form.numero} placeholder="Número" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        <Col sm={9}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicComplemento">
                <Form.Label>
                  <h6>Complemento</h6>
                </Form.Label>
                <Form.Control type="name" onChange={changeForm}  name="complemento" value={form.complemento} placeholder="Complemento" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Row>
        <Col className="col-cadastrar">
          <Button className="botao-final" onClick={CadastrarPacientes}>
            <h6>Cadastrar</h6>
          </Button>
        </Col>
        <Col className="col-voltar-paciente">
          <Button className="botao-voltar-paciente" href="/">
            <h6>Voltar</h6>
          </Button>
        </Col>
      </Row>
    </div>
  );
}
