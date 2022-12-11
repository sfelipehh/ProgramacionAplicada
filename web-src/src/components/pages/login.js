import * as React from 'react'
import { Box } from '@mui/system'
import CustomForm from '../customForm'
import { loginFields } from '../../data_models/dataModel'
import * as Yup from 'yup'
import { Paper } from '@mui/material'
import { navigate } from 'gatsby'
import { setUser } from '../../services/autentication'
const pageName = 'Iniciar Sesión'

const initialValues = {
  username:'',
  password:''
}

const validationSchema = Yup.object().shape(
  {
    username : Yup.string().trim().required('Nombre de Ususario Requerido'),
    password : Yup.string().trim().min(8,'La Contraseña debe ser de minimo 8 caracteres')
      .matches('[0-9a-zA-Z#$%&()=_*]+').required()
  }
)

const onSubmit = (values,actions)=>{
  setTimeout(() => {
    alert(JSON.stringify(values))
  }, 500);
  setUser()
  navigate('/index')
}

const Login = ()=>{
  return(
    <Box sx={{width:'30vw',height:'30vh'}}>
      <Paper elevation={2} >
        <CustomForm formName={pageName} 
          fields={loginFields}
          formType='creation'
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit = {onSubmit}
        />
        </Paper>
    </Box>
  )

}

export default Login
export const Head = () => <title>{pageName}</title>