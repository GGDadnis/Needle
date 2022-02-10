import { Context } from "../../../Context/AuthContext";
import axios from 'axios'
import api from "../../../Services/api";
import React,{useContext,useState,useEffect } from "react";
import { Form, Row, Col, Button } from "react-bootstrap";
import "./Styles.css";

export default function Edit_Usuario() {
  
  const {idUsuario,SetIdUsuario}= useContext(Context)

  console.log(idUsuario)

  const token = localStorage.getItem("tokenA")
  const [usuario,setUsuario]= useState()
  const[form, setForm] = useState({
    nome:"",
    email:"",
    senha:"",
    telefone:""})

    useEffect(() => {     

      api.get(`cadastro/usuarios/buscar/${idUsuario}`, {
        headers:{"Authorization":`Bearer ${token}`}
       })
         .then((response)=>{    
          setForm({...form,
            nome:response.data.nome,
            email:response.data.email,
            senha:form.senha,
            telefone:response.data.telefone
          
          })
  
          console.log(form)
                 setUsuario(response.data)
                 console.log(response.data)
         })
  
         .catch(() => {
             console.log("Erro no get")
  
         })
        },[])

        function changeForm(e){
          const {name, value} = e.target;
          console.log(form) 
          setForm({...form, [name]: value})
        
      }
      function Atualizar(){
        api.put(`http://localhost:8080/cadastro/usuarios/alterar/${usuario.nome}`,{ 

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
      





  return (
    <div className="">
      <Col sm={12}>
        <div>
          <Form>
            <Form.Group className="mb-2" controlId="formBasicNome">
              <Form.Label>
                <h6>Nome</h6>
              </Form.Label>
              <Form.Control
                type="name"
                placeholder={usuario?.nome} 
                name="nome"
                value={form.nome}
                onChange={changeForm} 
                
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
                   placeholder={usuario?.email} 
                   name="email"
                   value={form.email}
                   onChange={changeForm} 
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
                <Form.Control
                   type="name"
                   placeholder={usuario?.telefone} 
                   name="telefone"
                   value={form.telefone}
                   onChange={changeForm} />
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
              <Form.Control
                type="password"
                name="senha"
                value={form.senha}
                onChange={changeForm}  placeholder="digite a nova senha"/>
              
              <Form.Text className="text-muted"></Form.Text>
            </Form.Group>
          </Form>
        </div>
      </Col>
      <Row>
        <Col className="col-cadastrar">
          <Button className="botao-final" onClick={Atualizar}>
            <h6>Atualizar</h6>
          </Button>
        </Col>
        <Col className="col-voltar-usuario">
        <Button  href="/" className="botao-voltar-usuario">
         <h6>Voltar</h6>
        </Button>
      </Col>
      </Row>
    </div>
  );
}
