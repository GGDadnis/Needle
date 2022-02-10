import { Form, Row, Col, Button } from "react-bootstrap";
import "./Styles.css";
import { Context } from "../../../Context/AuthContext";
import axios from 'axios'
import api from "../../../Services/api";
import React,{useContext,useState,useEffect } from "react";

export default function Edit_Paciente() {

  const {idPaciente,SetIDPaciente}= useContext(Context)

  const [pacientes,setPaciente]= useState()
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

  console.log(idPaciente)

  function changeForm(e){
    const {name, value} = e.target;
    console.log(form) 
    setForm({...form, [name]: value})
  }

  useEffect(() => {        
    api.get(`pacientes/${idPaciente}`, {
      headers:{"Authorization":`Bearer ${token}`}
     })
       .then((response)=>{    
        setForm({...form,
          nomePaciente:response.data.nomePaciente,
          email:response.data.email,
          telefone:response.data.telefone,
          cpf:response.data.cpf,
          rg:response.data.rg,
          nascimentoPaciente:response.data.nascimentoPaciente,
          maePaciente:response.data.maePaciente,
          numero:response.data.numero,
          complemento:response.data.complemento,
          cep:response.data.endereco.cep,
          cidade:response.data.endereco.localidade,
          uf:response.data.endereco.uf,
          logradouro:response.data.endereco.logradouro,
          bairro:response.data.endereco.bairro
          
        })

        console.log(form)
               setPaciente(response.data)
               console.log(response.data)
       })

       .catch(() => {
           console.log("Erro no get")

       })
      },[])

  function Atualizar(){
      api.put(`pacientes/${pacientes.nomePaciente}`,{ 

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

                  }}, {
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
                <Form.Control type="name" name="nomePaciente" value={form.nomePaciente} onChange={changeForm} placeholder={pacientes?.nomePaciente} />
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
                <Form.Control type="name"name="cpf" value={form.cpf} onChange={changeForm} placeholder={pacientes?.cpf}/>
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
                <Form.Control type="name"name="rg" value={form.rg} onChange={changeForm}placeholder={pacientes?.rg} />
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
                <Form.Control type="name"name="maePaciente" value={form.maePaciente} onChange={changeForm} placeholder={pacientes?.maePaciente} />
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
                <Form.Control type="name" name="nascimentoPaciente" value={form.nascimentoPaciente} onChange={changeForm} 
                placeholder={pacientes?.nascimentoPaciente} />
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
                <Form.Control type="name" name="email" value={form.email} onChange={changeForm} placeholder={pacientes?.email} />
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
                <Form.Control type="name" name="telefone" value={form.telefone} onChange={changeForm} placeholder={pacientes?.telefone} />
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
                <Form.Control type="name" name="cep" onChange={changeForm} value={form.cep}  placeholder={pacientes?.endereco.cep} />
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
                <Form.Control type="name"value={form.cidade} placeholder={pacientes?.endereco.localidade}/>
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
                <Form.Control type="name" value={form.uf} placeholder={pacientes?.endereco.localidade}/>
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
                <Form.Control type="name" value={form.logradouro} placeholder={pacientes?.endereco.localidade} />
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
                <Form.Control type="name" value={form.bairro} placeholder={pacientes?.endereco.localidade}/>
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
                <Form.Control type="name" name="numero" value={form.numero} onChange={changeForm} placeholder={pacientes?.numero} />
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
                <Form.Control type="name" name="complemento" value={form.complemento} onChange={changeForm} placeholder={pacientes?.complemento} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Row>
        <Col className="col-cadastrar">
          <Button className="botao-final" onClick={Atualizar}>
            <h6>Atualizar</h6>
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
