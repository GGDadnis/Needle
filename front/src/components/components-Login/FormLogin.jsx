import React, { useContext, useState } from 'react';
import { useHistory } from 'react-router-dom'
import { Form, Button } from 'react-bootstrap'
import Logo from '../Logo'
import './Styles.css'
import axios from 'axios';

import { AuthProvider, Context } from '../../Context/AuthContext'
import api from '../../Services/api';
import apilog from '../../Services/apilog';
import { createContext } from 'react';

export default function FormLogin() {

    
    const { token, setToken } = useContext(Context)
    const {auth, setAuth} = useContext(Context);
    const [form, setForm] = useState({ usuario: '', senha: '' })
    const history = useHistory()

    async function handleLogin() {

        //console.log(auth);
            const params = new URLSearchParams();
            params.append('nome', form.usuario);
            params.append('senha', form.senha);

            const retorno = await api.post('login', params)

            console.log(retorno.data.tokenacesso)
           
            setTok(retorno.data.tokenacesso,retorno.data.tokenrefresh ).then(
                console.log(token.tokenAcesso),
                chLocalAuth(true),
                chLocalTok(retorno.data.tokenacesso).then(console.log(token.tokenAcesso))
            )
           
            //localStorage.setItem("tokenA", token.tokenAcesso)
    
            //api.defaults.headers.Authorization = `Bearer ${retorno.data.tokenacesso}`
    
           history.push("/")   

       
        }
       async function setTok (B,C){
        await setToken({
            tokenAcesso: B,
            tokenRefresh: C
        })
       }

       async function chLocalTok(tok){
        await localStorage.setItem("tokenA",tok)
       }

       async function chLocalAuth(auti){
           await localStorage.setItem("auth", auti)
       }

    

    function changeForm(e) {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value })
        console.log(form);
    }

    const validarLogin = (e) => {
        e.preventDefault();
        try {
            handleLogin()
        } catch (erro) {
            alert("dados invalidos!")
        }

    }


    return (
        <div className='login-main-container'>
            <Logo />
            <h2 >LOGIN</h2>
            <Form className="login-input" onSubmit={validarLogin}>
                <Form.Group className="mb-3" controlId="formBasicEmail">

                    <Form.Label >Usuário</Form.Label>
                    <Form.Control
                        type="name"
                        name="usuario"
                        value={form.usuario}
                        placeholder="Entre com seu usuário"
                        onChange={changeForm}
                    />
                    <Form.Text
                        className="text-muted"

                    >
                    </Form.Text>

                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">

                    <Form.Label >Senha</Form.Label>
                    <Form.Control
                        type="password"
                        name="senha"
                        value={form.senha}
                        placeholder="Entre com sua Senha"
                        onChange={changeForm}
                    />
                    <Form.Text
                        className="text-muted"

                    >
                    </Form.Text>

                </Form.Group>

                <Button variant="outline-primary" size="lg" type="submit" md="auto">
                    ENTRAR
                </Button>
            </Form>

        </div>
    )

}