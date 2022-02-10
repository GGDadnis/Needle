
import React, {useState, useEffect, useContext} from 'react'

import { Navbar, Nav, Container, Form, FormControl, Button, Offcanvas,NavDropdown } from 'react-bootstrap';
import './Styles.css'
import sair from '../assets/sair.png'
import perfil from '../assets/perfil.png'

import api from '../Services/api'
import{Context} from '../Context/AuthContext'


export default function NavBar(){

  
    return(

             <div className='main-container-NavBar' >
                <Navbar className='nav-main' bg="" expand={false} >
                            <Container fluid >
                                        <Navbar.Brand className='needle-navBar'>                                      
                                        <a href='/'><text>Needle</text></a>
                                        <div className='container-opcoes'>
                                          <NavDropdown title="Médico" id="basic-nav-dropdown">
                                                <NavDropdown.Item href="/lista-medicos">Lista de Médicos</NavDropdown.Item>
                                                <NavDropdown.Item href="/cadastro-medicos">Cadastrar Médicos</NavDropdown.Item>
                                               
                                            </NavDropdown>
                                            <NavDropdown title="Paciente" id="basic-nav-dropdown">
                                                <NavDropdown.Item href="/lista-pacientes">Lista de Pacientes</NavDropdown.Item>
                                                <NavDropdown.Item href="/cadastro-pacientes">Cadastrar Paciente</NavDropdown.Item>                                          
                                              
                                            </NavDropdown>
                                            <NavDropdown title="Usuário" id="basic-nav-dropdown">
                                                <NavDropdown.Item href="/lista-usuarios">Lista de Usuário</NavDropdown.Item>
                                                <NavDropdown.Item href="/cadastro-usuarios">Cadastrar Usuário</NavDropdown.Item>
                                               
                                            </NavDropdown>
                                            <NavDropdown title="Recibo" id="basic-nav-dropdown">
                                                <NavDropdown.Item href="/lista-recibos">Historico de Recibos</NavDropdown.Item>
                                                <NavDropdown.Item href="/cadastro-recibos">Criar um Recibo</NavDropdown.Item>
                                         </NavDropdown>
                                         </div>
                                         </Navbar.Brand>
                                       
                        
                                        <Navbar.Toggle aria-controls="offcanvasNavbar" />
                                        <Navbar.Offcanvas
                                        id="offcanvasNavbar"
                                        aria-labelledby="offcanvasNavbarLabel"
                                        placement="end"           
                                        >
                                            
                                        <Offcanvas.Header closeButton>
                                            <Offcanvas.Title id="offcanvasNavbarLabel">Needle options</Offcanvas.Title>
                                        </Offcanvas.Header>
                                        
                                        <Offcanvas.Body >
                                            <Nav className="nav-button justify-content-end flex-grow-1 pe-3" >                                
                                                <p>Sair</p>                    
                                                <Nav.Link href="/login"><button><img className='icon-navBar' src={sair}/></button></Nav.Link>                  
                                                  <div className='responsive-container'>
                                                    <NavDropdown title="Medico" id="nav-basic">
                                                    <NavDropdown.Item href="/lista-medicos">Lista de Medicos</NavDropdown.Item>
                                                    <NavDropdown.Item href="/cadastro-medicos">Cadastrar Medicos</NavDropdown.Item>
                                                
                                                </NavDropdown>
                                                <NavDropdown title="Paciente" id="nav-basic">
                                                    <NavDropdown.Item href="/lista-pacientes">Lista de Pacientes</NavDropdown.Item>
                                                    <NavDropdown.Item href="/cadastro-pacientes">Cadastrar Paciente</NavDropdown.Item>
                                                
                                                </NavDropdown>
                                                <NavDropdown title="Recibo" id="nav-basic">
                                                    <NavDropdown.Item href="/lista-recibos">Historico de Recibos</NavDropdown.Item>
                                                    <NavDropdown.Item href="/cadastro-recibos">Criar um Recibo</NavDropdown.Item>
                                            </NavDropdown>
                                             </div>
                                                                            
                                            </Nav>                                           
                                        </Offcanvas.Body>
                                        </Navbar.Offcanvas>
                                        
                       
                            </Container>
                            
                </Navbar>


            </div>
    )
}