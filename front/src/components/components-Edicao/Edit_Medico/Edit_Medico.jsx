import { Form, Row, Col, Button } from "react-bootstrap";
import "./Styles.css";
import React,{useContext,useState,useEffect } from "react";
import api from "../../../Services/api";
import { Context } from "../../../Context/AuthContext";
import axios from 'axios'

export default function Edit_Medico() {

  const {idMedico,SetIDMedico}= useContext(Context)

  const[medicos,setMedicos] = useState()
  const token = localStorage.getItem("tokenA")

  const [form, setForm] = useState({
    nomeMedico:"",
    cpf: "",
    crm:"",
    profissao:"",
    emailMedico:"",
    telefone:"",
    nascimentoMedico:"",
    cep:"",
    cidade:"",
    uf:"",
    logradouro:"",
    bairro:""})

  console.log(idMedico)

  function changeForm(e){
    const {name, value} = e.target;
    console.log(form) 
    setForm({...form, [name]: value})
  
}

  useEffect(() => {        

    api.get(`medicos/buscar/${idMedico}`, {
          headers:{"Authorization":`Bearer ${token}`}
         })
       .then((response)=>{
        setForm({...form,
          nomeMedico:response.data.nomeMedico,
          cpf:response.data.cpf,
          crm:response.data.crm,
          profissao:response.data.profissao,
          emailMedico:response.data.emailMedico,
          telefone:response.data.telefone,
          nascimentoMedico:response.data.nascimentoMedico,
          cep:response.data.endereco.cep,
          cidade:response.data.cidade,
          uf:response.data.uf,
          logradouro:response.data.logradouro,
          bairro:response.data.bairro
        
        })
        console.log(form)
               setMedicos(response.data)
               console.log(response.data)
       })

       .catch(() => {
           console.log("Erro no get")

       })
      },[])

      function Atualizar(){
        api.put(`http://localhost:8080/medicos/cadastrar/atualizar?nomeMedico=${medicos.nomeMedico}`,{ 

                  nomeMedico:form.nomeMedico,
                  cpf:form.cpf,
                  crm:form.crm,
                  profissao:form.profissao,
                  emailMedico:form.emailMedico,
                  telefone:form.telefone,
                  nascimentoMedico:form.nascimentoMedico,
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

        

  return (
    <div className="">
      <Form>
        <Row>
          <Col>
            <div>
              <Form.Group className="mb-2" controlId="formBasicNome">
                <Form.Label>
                  <h6>Nome</h6>
                </Form.Label>
                <Form.Control type="name" name="nomeMedico" value={form.nomeMedico} onChange={changeForm} placeholder={medicos?.nomeMedico} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
        </Row>
        <Row>
          <Col sm={6}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicCPF">
                <Form.Label >
                  <h6>CPF</h6>
                </Form.Label>
                <Form.Control type="name" name="cpf" value={form.cpf} onChange={changeForm} placeholder={medicos?.cpf} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
          <Col sm={6}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicCRM">
                <Form.Label>
                  <h6>CRM</h6>
                </Form.Label>
                <Form.Control type="name" name="crm"value={form.crm} onChange={changeForm}placeholder={medicos?.crm} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
        </Row>
        <Row>
          <Col>
            <div>
              <Form.Group className="mb-2" controlId="formBasicEspecialidade">
                <Form.Label>
                  <h6>Especialidade</h6>
                </Form.Label>
                <Form.Control
                  type="name"
                  placeholder={medicos?.profissao}
                  value={form.profissao}
                  name="profissao"
                  onChange={changeForm}
                />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
        </Row>
        <Row>
          <Col sm={6}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicEmail">
                <Form.Label>
                  <h6>Email</h6>
                </Form.Label>
                <Form.Control type="name" value={form.emailMedico} name="emailMedico" onChange={changeForm} placeholder={medicos?.emailMedico}/>
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
          <Col sm={6}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicTelefone">
                <Form.Label>
                  <h6>Telefone</h6>
                </Form.Label>
                <Form.Control type="name" name="telefone"onChange={changeForm} value={form.telefone} placeholder={medicos?.telefone} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
        </Row>
        <Row>
          <Col sm={6}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicNascimento">
                <Form.Label>
                  <h6>Data de nascimento</h6>
                </Form.Label>
                <Form.Control type="name" name="nascimentoMedico" onChange={changeForm} value={form.nascimentoMedico} placeholder={medicos?.nascimentoMedico}/>
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
        </Row>
        <Row>
          <Col sm={6}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicEmail">
                <Form.Label>
                  <h6>CEP</h6>
                </Form.Label>
                <Form.Control type="name" onChange={changeForm} value={form.cep} name="cep" placeholder={medicos?.endereco.cep} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
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
              <Form.Group className="mb-2" controlId="formBasicCidade">
                <Form.Label>
                  <h6>Cidade</h6>
                </Form.Label>
                <Form.Control type="name" value={form.cidade} placeholder={medicos?.endereco.localidade} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
          <Col sm={3}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicUF">
                <Form.Label>
                  <h6>UF</h6>
                </Form.Label>
                <Form.Control type="name" value={form.uf} placeholder={medicos?.endereco.uf}  />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
        </Row>
        <Row>
          <Col sm={6}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicLogradouro">
                <Form.Label>
                  <h6>Logradouro</h6>
                </Form.Label>
                <Form.Control type="name"value={form.logradouro}  placeholder={medicos?.endereco.logradouro} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
          <Col sm={6}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicBairro">
                <Form.Label>
                  <h6>Bairro</h6>
                </Form.Label>
                <Form.Control type="name"value={form.bairro}  placeholder={medicos?.endereco.bairro}  />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
        </Row>
        <Row>
          <Col sm={3}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicNumero">
                <Form.Label>
                  <h6>Número</h6>
                </Form.Label>
                <Form.Control type="name" placeholder="Número" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
          <Col sm={9}>
            <div>
              <Form.Group className="mb-2" controlId="formBasicComplemento">
                <Form.Label>
                  <h6>Complemento</h6>
                </Form.Label>
                <Form.Control type="name" placeholder="Complemento" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </div>
          </Col>
        </Row>
        <Row>
          <Col className="col-cadastrar">
            <Button className="botao-final" onClick={Atualizar}>
              <h6>Atualizar</h6>
            </Button>
          </Col>
          <Col className="col-voltar-medico">
            <Button type="button" className="botao-voltar-medico" href="/">
              <h6>Voltar</h6>
            </Button>
          </Col>
        </Row>
      </Form>
    </div>
  );
}
