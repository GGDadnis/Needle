import React from "react";
import AreaCadastro from "../../components/components-Home/AreaCadastro";
import BemVindo from "../../components/components-Home/Bemvindo";
import NavBar from "../../components/NavBar";

import './Styles.css'
export default function Home(){

    

    return(
        <div className="main-container-Home">
            <div>
                <NavBar/>
                <div className='bemVindo-main-container'>
                <BemVindo/>
                </div>
                <AreaCadastro/>
            </div>
        </div>
    )

 
}