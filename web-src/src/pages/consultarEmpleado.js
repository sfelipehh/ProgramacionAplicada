import * as React from 'react'
import { IconButton, TextField, Box, Skeleton} from '@mui/material'
import { Search } from '@mui/icons-material'
import DisplayData from '../components/displayData'
import Layout from '../components/layout'
import { empleadoFields } from '../data_models/dataModel'
import * as Yup from 'yup'
import { useFormik } from 'formik'

const pageName = 'Consultar Empleado'

const getData = (id,stateChangeFunction,valuesRef)=>{
  setTimeout(()=>stateChangeFunction(true),500)
  valuesRef.current = {
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
}

const ConsultarEmpleado = ()=>{

  const [infoLoaded,setLoaded] = React.useState(false)
  const valuesRef = React.useRef(null)
  const idFormik = useFormik({
    initialValues : {id:''},
    onSubmit : ({id})=> getData(id,setLoaded,valuesRef),
    validationSchema : Yup.object().shape({id:Yup.number().positive().required("Ingresa un Id")})
  })
  
  return(
    <Layout pageName={pageName}>
      <Box sx={{px:2}}>
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
      {infoLoaded ? <DisplayData fields={empleadoFields} data={valuesRef.current} /> : <Skeleton sx={{p:2}} width={360} height={360} /> }
    </Layout>
  )

}

export default ConsultarEmpleado
export const Head = ()=><title>{pageName}</title>