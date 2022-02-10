import { Form, Row, Col, Button } from "react-bootstrap";
import "./Styles.css";
import api from '../../../Services/api'
import axios from 'axios'
import React, {useState} from 'react'
export default function Form_Recibo() {

  const token = localStorage.getItem("tokenA")
  const [form, setForm] = useState({
    nomeMedico:"",
    nomePaciente:"",
    servico:"",
    valor:"",


  })
  const [verificar, setVerificar] = useState(false)
  const [layout, setLayout] = useState(0, [])

  
  function changeVerificar(){

  console.log('entrou')

  if(verificar == false){
    
    console.log("entrou no if")
    setVerificar(true)


  }else{
    console.log("entrou no else")
    setVerificar(false)
  }

   console.log(verificar)

}



  function gerarRecibo(){
      api.post("recibos/pdf",{          
        nomeMedico:form.nomeMedico,
        nomePaciente:form.nomePaciente,
        servico:form.servico,
        valor:form.valor,
        tipoLayout:layout,
        exibicaoCampos:{
          exibeNomeMedico:true,
          exibeNomePaciente: true,
          exibeServico:true,
          exibeValor:true
        }
       }, {
        headers:{"Authorization":`Bearer ${token}` },
        responseType:"blob"
       }).then(response => {
               
      let blob = new Blob([response.data], {
      type: 'application/pdf'
    });
    let url = window.URL.createObjectURL(blob)
    window.open(url);
      }).catch((erro)=>{
        console.log(erro)
        alert("um dos campos ta errado amigão")
      })
  }

  function changeForm(e){
    const {name, value} = e.target;

    setForm({...form, [name]: value})
  
}

  const changeLayoutReport = ()=>{
    if(layout == 0){
      setLayout(1);
    }else{
      if(layout == 1){
        setLayout(0)
      }
    }
    console.log(layout);
  }



  return (
    <div className="">
      <Col sm={12}>
        <div>
          <Form>
            <Form.Group className="mb-2" controlId="formBasicPaciente">
              <Form.Label>
                <h6>Nome do Paciente</h6>
              </Form.Label>
              <Form.Control
                type="name"
                placeholder="Digite o nome do paciente"
                name="nomePaciente"
                 value={form.nomePaciente}
                  onChange={changeForm}
              />
              <Form.Text className="text-muted"></Form.Text>
            </Form.Group>
          </Form>
        </div>
      </Col>
      <Col sm={12}>
        <div>
          <Form>
            <Form.Group className="mb-2" controlId="formBasicMedico">
              <Form.Label>
                <h6>Nome do Médico</h6>
              </Form.Label>
              <Form.Control type="name" name="nomeMedico" value={form.nomeMedico} onChange={changeForm} placeholder="Digite o nome do médico" />
              <Form.Text className="text-muted"></Form.Text>
            </Form.Group>
          </Form>
        </div>
      </Col>
      <Row>
        <Col sm={6}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicServico">
                <Form.Label>
                  <h6>Serviço</h6>
                </Form.Label>
                <Form.Control
                  type="name"
                  placeholder="Digite o serviço realizado"
                  name="servico" 
                  value={form.servico}
                   onChange={changeForm}
                />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        <Col sm={4}>
          <div>
            <Form>
              <Form.Group className="mb-2" controlId="formBasicValor">
                <Form.Label>
                  <div className="container-valor">
                  <h6>Valor</h6>
                  <Form.Check aria-label="option 1" onClick={changeVerificar}/>
                  </div>
                </Form.Label>
                <Form.Control type="name" name="valor" value={form.valor} onChange={changeForm} placeholder="Digite o valor" />
                <Form.Text className="text-muted"></Form.Text>
              </Form.Group>
            </Form>
          </div>
        </Col>
        <Col sm={4}>
          <div>
            <p className="mb-2">
              mudar o layout
            </p>
          <Button className="botao-pdf" onClick={changeLayoutReport}>
            <h6>{layout}</h6>
          </Button>
          

          </div>
        </Col>
      </Row>
      <Row>
        <Col className="col-pdf">
          <Button className="botao-pdf" onClick={gerarRecibo}>
            <h6>Gerar PDF</h6>
          </Button>
        </Col>
      </Row>
      <Col className="col-voltar-recibo">
        <Button className="botao-voltar-recibo" href="/">
          <h6>Voltar</h6>
        </Button>
      </Col>
    </div>
  );
}