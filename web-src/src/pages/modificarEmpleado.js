import * as React from 'react'
import * as Yup from 'yup'
import Layout from '../components/layout'
import CustomForm from '../components/customForm'
import SekeletonForm from '../components/skeletonForm'
import { IconButton, TextField, Box } from '@mui/material'
import { empleadoFields } from '../data_models/dataModel'
import { useFormik } from 'formik'
import { Search } from '@mui/icons-material'
const pageName = "Modificar Empleado"


const onSubmit = async (values,actions)=>{
  alert(JSON.stringify(values,null,2))
  /*await fetch(
    'http://localhost:8080/demo/add',
    {
      method:'POST',
      mode:'cors',
      headers: {
        'Content-Type':'application/json'
      },
      body : JSON.stringify(values,null,2)
    }
  ).then(value =>
    console.log(value.status)
  )*/
}

const loadInfo = (id,stateChangeFunction,initialSaveRef) => {
  setTimeout(() => {
    stateChangeFunction(true)
  }, 500)
  console.log('Load Info')
  initialSaveRef.current = {
    identificacion : 5667,
    nombres : 'Samuel',
    apellidos  : 'Hernandez',
    cargo: 'Admin',
    celular : '45567',
    correo : 'sfelipehh@gmail.com',
    fechaNacimiento : '2004-02-14',
    idCuadrilla : 67, 
    idSede : 8,
    cupoAsignado : 100
  }
  /*let info
  await fetch(
    'http://localhost:8080/demo/get?id={id}',
    {
      method:'GET',
      mode:'cors',
      headers: {
        'Content-Type':'application/json'
      },
      body : JSON.stringify(values,null,2)
    }
  ).then(value =>{
    console.log(value.status)
    info = value
    }
  )
  return info*/
}


const ModificarEmpleado = () => {
  const [infoLoaded,changeLoaded] = React.useState(false)
  const savedInitialValues = React.useRef(null)
  const idFormik = useFormik({
    initialValues : {id:''},
    onSubmit : ({id})=> loadInfo(id,changeLoaded,savedInitialValues),
    validationSchema : Yup.object().shape({id:Yup.number().positive().required("Ingresa un Id")})
  })
  const validationSchema = Yup.object().shape(
    {
      identificacion : Yup.number().positive().required("Identificación Requerida"),
      nombres : Yup.string().required("Nombres Requeridos"),
      apellidos : Yup.string().required("Apellidos Requeridos"),
      cargo : Yup.string().required("Cargo Requerido") ,
      celular : Yup.string()
      .matches("[0-9]{3}-[0-9]{7}","El celuar de ser xxx-xxxxxxx")
      .required("Celular Requerido"),
      correo : Yup.string().email("Correo Invalido (ej@emp.lo)").required("Correo Electronico Requerido"),
      fechaNacimiento : Yup.date().required("Fecha de Nacimiento Requerida"),
      idCuadrilla : Yup.number().positive().optional(),
      idSede : Yup.number().positive().required("Id de Sede Requerido"),
      cupoAsignado : Yup.number().positive().optional()
    }
  )

  return (
    <Layout pageName={pageName} >
      <Box sx={{p:2}}>
        <form onSubmit={idFormik.handleSubmit} style={{display:'flex'}}>
          <TextField margin='dense' variant='outlined'
            name='id' 
            id='idEmpleado' 
            type='number'
            label='Id Empleado' 
            required 
            value={idFormik.values.id} 
            onChange={idFormik.handleChange}
            error={idFormik.touched.id && Boolean(idFormik.errors.id)}
            helperText={idFormik.touched.id && idFormik.errors.id}
          />
          <IconButton type='submit'>
            <Search />
          </IconButton>
        </form>
      </Box>
      {infoLoaded ? 
      <CustomForm formName={pageName} formType='modify'
        fields={empleadoFields}
        initialValues={savedInitialValues.current}
        validationSchema={validationSchema}
        onSubmit={onSubmit}/> 
        : <SekeletonForm formName={pageName} fields={empleadoFields} />
      }
    </Layout>
  )
}

export default ModificarEmpleado
export const Head = ()=><title>{pageName}</title>
