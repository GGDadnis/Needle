
import { Button } from 'react-bootstrap';
import FormLogin from '../../components/components-Login/FormLogin';
import Logo from '../../components/Logo'
import medicos from '../../assets/medicos.jpg'
import './Styles.css'



export default function Login() {
 
    return(

        <div className='main-container-full'>  

            <div className="main-container">    

                <div className="form-login-container">  

                    <FormLogin/>

                </div>           

         <img id='login'src={medicos}/>

            </div>
    
        </div>
    )

}
