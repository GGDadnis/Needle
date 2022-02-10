import './Styles.css'
import iconBem from '../../assets/iconBem.png'

export default function BemVindo(){

    return(

        <div className='Main-bemvindo-container'>
         <h1>Bem-vindo <img className='iconBem-main' src={iconBem}/></h1>
         
        </div>
    )

}