import * as React from 'react'
import * as Yup from 'yup'
    
import CustomForm from '../customForm'
import SekeletonForm from '../skeletonForm'
import { IconButton, TextField, Box } from '@mui/material'
import { cuadrillaFields } from '../../data_models/dataModel'
import { useFormik } from 'formik'
import { Search } from '@mui/icons-material'
const pageName = "Modificar Cuadrilla"


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

const loadInfo = (stateChangeFunction,initialSaveRef) => {
  setTimeout(() => {
    stateChangeFunction(true)
  }, 500)
  console.log('Load Info')
  initialSaveRef.current = {
    nombre : 'Cuadrilla1',
    idSede : 2,
    idSupervisor : 5,
    cantidadEmpleados : 3,
    cupoAsignado : 100,
    empleados : [1,2,4],
    localidades : [6,7,8]
  }
  /*let info
  await fetch(
    'http://localhost:8080/demo/get',
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


const ModificarCuadrilla = () => {
  const [infoLoaded,changeLoaded] = React.useState(false)
  const savedInitialValues = React.useRef(null)
  const idFormik = useFormik({
    initialValues : {id:''},
    onSubmit : ()=> loadInfo(changeLoaded,savedInitialValues),
    validationSchema : Yup.object().shape({id:Yup.number().positive().required("Ingresa un Id")})
  })
  const validationSchema = Yup.object().shape(
    {
      nombre : Yup.string().required('Nombre de Cuadrilla Requerido'),
      idSede : Yup.number().positive().required('Id de Sede Requerido'),
      idSupervisor : Yup.number().positive().required('Id de Empleado Supervisor Requerido'),
      cantidadEmpleados : Yup.number().positive().required('Cantidad de Empleados Requerida'),
      cupoAsignado : Yup.number().positive().optional(),
      empleados : Yup.array().of(Yup.number().positive()).length(Yup.ref('cantidadEmpleados')),
      localidades : Yup.array().of(Yup.number().positive())
    }
  )

  return (
      <>
      <Box sx={{p:2}}>
        <form onSubmit={idFormik.handleSubmit} style={{display:'flex'}}>
          <TextField margin='dense' variant='outlined'
            name='id' 
            id='idCuadrilla' 
            type='number'
            label='Id Cuadrilla' 
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
        fields={cuadrillaFields}
        initialValues={savedInitialValues.current}
        validationSchema={validationSchema}
        onSubmit={onSubmit}/> 
        : <SekeletonForm formName={pageName} fields={cuadrillaFields} />
      }
      </>
  )
}

export default ModificarCuadrilla
export const Head = ()=><title>{pageName}</title>
