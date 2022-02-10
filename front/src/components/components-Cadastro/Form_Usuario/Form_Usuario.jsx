
import { Form, Row, Col, Button } from "react-bootstrap";
import "./Styles.css";
import axios from 'axios'
import React, {useState} from 'react'
import api from '../../../Services/api'
import { useEffect } from "react";
export default function Form_Usuario() {

  const token = localStorage.getItem("tokenA")
  const [adm, setAdm] = useState(false) 
  const[form, setForm] = useState({
    nome:"" ,
    email:"" ,
    senha:"" ,
    telefone:""
  })
 useEffect(()=>{
  api.get("cadastro/usuarios/verifica", {
    headers:{"Authorization":`Bearer ${token}`}
   })
     .then((response)=>{
             setAdm(response.data)
             
     })
      console.log(adm) 
 },[])

    function CadastrarPacientes(){
      api.post("cadastro/usuarios/inserir",{
                nome:form.nome,
                email:form.email,
                senha:form.senha,
                telefone:form.telefone
         }, {
          headers:{"Authorization":`Bearer ${token}`}
         }
          
        ).then(response => console.log(response)).catch((erro)=>console.log(erro))
        window.location.reload()
    }

    

    function setauto(a){
      localStorage.setItem("auto", a)
    }

    function changeForm(e){

      const {name, value} = e.target;
      console.log(form) 
      setForm({...form, [name]: value})
      console.log(form)
  
    }

  return (
    <div className="">
      {adm?(
        <>
       
 <Col sm={12}>
        <div>
          <Form>
            <Form.Group className="mb-2" controlId="formBasicNome">
              <Form.Label>
                <h6>Nome</h6>
              </Form.Label>
              <Form.Control
                type="name"
                name="nome"
                placeholder="Digite o nome"
                onChange={changeForm}
                value={form.nome} 
              />
              <Form.Text className="text-muted"></Form.Text>
            </Form.Group>
          </Form>
        </div>
      </Col>
      <Row>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicEmail">
                <Form.Label>
                  <h6>Email</h6>
                </Form.Label>
                <Form.Control
                  type="name"
                  name="email"
                  placeholder="Digite o email"
                  onChange={changeForm}
                  value={form.email} 
                />
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
                <Form.Control type="name" name="telefone"placeholder="Digite o telefone" onChange={changeForm} value={form.telefone} />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
      </Row>
      <Col sm={8}>
        <div>
          <Form>
            <Form.Group className="mb-2" controlId="formBasicSenha">
              <Form.Label>
                <h6>Senha</h6>
              </Form.Label>
              <Form.Control type="name"name="senha" placeholder="Digite a senha" onChange={changeForm} value={form.senha} />
              <Form.Text className="text-muted"></Form.Text>
            </Form.Group>
          </Form>
        </div>
      </Col>
      <Row>
        <Col className="col-cadastrar">
          <Button className="botao-final" onClick={CadastrarPacientes}>
            <h6>Cadastrar</h6>
          </Button>
        </Col>
        <Col className="col-voltar-usuario">
        <Button className="botao-voltar-usuario" href="/">
          <h6>Voltar</h6>
        </Button>
      </Col>
      </Row>
        </>
      ):(
        <>
        <h2>acesso negado</h2>
        </>
      )}
    </div>
  );
}
