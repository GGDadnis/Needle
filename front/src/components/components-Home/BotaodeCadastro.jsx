import React, {useState, useEffect} from 'react'
import api from '../../Services/api'
import'../Styles.css'
import { Button } from 'react-bootstrap'

//esse é o ,meu
export default function BotaodeCadastro(){
    
    const [pacientes, setPacientes] = useState()
    const [medicos, setMedicos] = useState()
    const [recibos, setRecibos] = useState()
   
    const token = localStorage.getItem("tokenA")

     useEffect(() => {
           console.log(`Bearer ${localStorage.getItem("tokenA")}`);
           
             api.get("http://localhost:8080/pacientes?isDeleted=false", {
                 headers:{"Authorization":`Bearer ${token}`}
                })
                .then((response)=>{
                
                        setPacientes(response.data)
                        console.log(response.data)
                })

                .catch(() => {
                    console.log("não funcionou")

                })
    
        },[])
        useEffect(() => {
        
             api.get("http://localhost:8080/medicos?isDeleted=false",{
                headers:{"Authorization":`Bearer ${token}`}
               })
                .then((response)=>{
                        setMedicos(response.data)
                        console.log(response.data)
                })

                .catch(() => {
                    console.log("não funcionou")

                })
    
        },[])
        useEffect(() => {
        
             api.get("http://localhost:8080/recibos",{
                headers:{"Authorization":`Bearer ${token}`}
               })
                .then((response)=>{
                        setRecibos(response.data)
                        console.log(response.data)
                })

                .catch(() => {
                    console.log("não funcionou")

                })
    
        },[])
    
    return(

        <div className='div-container-botao'>
   
       
          
                <div className='botao-container-cadastro'>
                <h4>Medicos</h4>
                <div className='linha' ></div>
                <a style={{}}href='/lista-medicos'><h3>{medicos?.length}</h3></a> 

            </div> 

            <div className='botao-container-cadastro'>
                <h4 >Pacientes</h4>
                <div className='linha' ></div>
                <a style={{}}href='/lista-pacientes'><h3>{pacientes?.length}</h3></a>

            </div> 

            <div  className='botao-container-cadastro'>
              
              <h4>Recibos</h4>
              <div className='linha'  ></div>
              <a style={{}}href='/lista-recibos'> <h3>{recibos?.length}</h3></a> 

             </div> 
        

              

            
        </div>
    )
}