import { Form, Row, Col, Button } from "react-bootstrap";
import "./Styles.css";
import api from "../../../Services/api";
import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";



export default function Form_Medico() {

  const [adm, setAdm] = useState(false)

  useEffect(() => {
    api.get("cadastro/usuarios/verifica", {
      headers: { "Authorization": `Bearer ${token}` }
    })
      .then((response) => {
        setAdm(response.data)

      })
    console.log(adm)
  }, [])

  const token = localStorage.getItem("tokenA")

  const [form, setForm] = useState({
    nomeMedico: "",
    cpf: "",
    crm: "",
    profissao: "",
    emailMedico: "",
    telefone: "",
    nascimentoMedico: "",
    cep: "",
    cidade: "",
    uf: "",
    logradouro: "",
    bairro: ""
  })


  function Cadastrar() {
    api.post("medicos/cadastrar/inserir", {

      nomeMedico: form.nomeMedico,
      cpf: form.cpf,
      crm: form.crm,
      profissao: form.profissao,
      emailMedico: form.emailMedico,
      telefone: form.telefone,
      nascimentoMedico: form.nascimentoMedico,
      endereco: {
        cep: form.cep
      }
    }, {
      headers: { "Authorization": `Bearer ${token}` }
    }


    ).then(response => console.log(response)).catch((erro) => console.log(erro))
    window.location.reload()
  }
  function ValidarCep() {

    axios.get("https://viacep.com.br/ws/" + form.cep + "/json/")
      .then(response => {

        setForm({ ...form, cidade: response.data.localidade, uf: response.data.uf, logradouro: response.data.logradouro, bairro: response.data.bairro })
      })



  }
  function changeForm(e) {
    const { name, value } = e.target;
    console.log(form)
    setForm({ ...form, [name]: value })


  }
  return (
    <div className="">
      {adm ? (
        <>
          <Form>
            <Row>
              <Col>
                <div>
                  <Form.Group className="mb-2" controlId="formBasicNome">
                    <Form.Label>
                      <h6>Nome</h6>
                    </Form.Label>
                    <Form.Control type="name" name="nomeMedico" value={form.nomeMedico} onChange={changeForm} placeholder="Digite o nome" />
                    <Form.Text className="text-muted"></Form.Text>
                  </Form.Group>
                </div>
              </Col>
            </Row>
            <Row>
              <Col sm={6}>
                <div>
                  <Form.Group className="mb-2" controlId="formBasicCPF">
                    <Form.Label>
                      <h6>CPF</h6>
                    </Form.Label>
                    <Form.Control type="name" name="cpf" value={form.cpf} onChange={changeForm} placeholder="Digite o CPF" />
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
                    <Form.Control type="name" name="crm" placeholder="Digite o CRM" value={form.crm} onChange={changeForm} />
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
                      name="profissao"
                      placeholder="Digite a especialidade"
                      onChange={changeForm}
                      value={form.profissao}
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
                    <Form.Control type="name" name="emailMedico" placeholder="Digite o email" value={form.emailMedico} onChange={changeForm} />
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
                    <Form.Control type="name" name="telefone" placeholder="Digite o telefone" value={form.telefone} onChange={changeForm} />
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
                    <Form.Control type="name" name="nascimentoMedico" placeholder="Data de nascimento" value={form.nascimentoMedico} onChange={changeForm} />
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
                    <Form.Control type="name" name="cep" placeholder="Digite o CEP" value={form.cep} onChange={changeForm} />
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
                    <Form.Control type="name" name="cidade" value={form.cidade} placeholder="Cidade" />
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
                    <Form.Control type="name" value={form.uf} placeholder="UF" />
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
                    <Form.Control type="name" value={form.logradouro} placeholder="Logradouro" />
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
                    <Form.Control type="name" value={form.bairro} placeholder="Bairro" />
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
                <Button className="botao-final" onClick={Cadastrar}>
                  <h6>Cadastrar</h6>
                </Button>
              </Col>
              <Col className="col-voltar-medico">
                <Button type="button" className="botao-voltar-medico" href="/">
                  <h6>Voltar</h6>
                </Button>
              </Col>
            </Row>
          </Form>
        </>
      ) : (
        <>
        <h2>acesso negado</h2>
        </>
      )}
    </div>
  );
}
