import * as React from 'react'
import { Box } from '@mui/system'
import CustomForm from '../components/customForm'
import { loginFields } from '../data_models/dataModel'
import * as Yup from 'yup'

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

}

const Login = ()=>{
  return(
    <Box sx={{width:'30vw',height:'30vh'}}>
      <form>
        <CustomForm formName={pageName} 
          fields={loginFields}
          formType='creation'
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit = {onSubmit}
        />
      </form>
    </Box>
  )

}

export default Login
export const Head = () => <title>{pageName}</title>