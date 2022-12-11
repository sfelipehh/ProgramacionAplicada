import * as React from 'react'
import * as Yup from 'yup'
import CustomForm from '../customForm'
import SekeletonForm from '../skeletonForm'
import { IconButton, TextField, Box } from '@mui/material'
import { localidadFields } from '../../data_models/dataModel'
import { useFormik } from 'formik'
import { Search } from '@mui/icons-material'
const pageName = "Modificar Localidad"


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
    nombre : 'Localidad1',
    calleInicio : 'Calle1',
    calleFin : 'Calle2',
    carreraInicio : 'Carrera1',
    carreraFin : 'Carrera2',
    idSede : 4
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


const ModificarLocalidad = () => {
  const [infoLoaded,changeLoaded] = React.useState(false)
  const savedInitialValues = React.useRef(null)
  const idFormik = useFormik({
    initialValues : {id:''},
    onSubmit : ()=> loadInfo(changeLoaded,savedInitialValues),
    validationSchema : Yup.object().shape({id:Yup.number().positive().required("Ingresa un Id")})
  })
  const validationSchema = Yup.object().shape(
    {
      nombre : Yup.string().required('Nombre de Localiad Requerido'),
      calleInicio : Yup.string().required('Calle de Inicio Requerida'),
      calleFin : Yup.string().required('Calle de Finalización Requerida'),
      carreraInicio : Yup.string().required('Carreda de Inicio Requerida'),
      carreraFin : Yup.string().required('Carreda de Finalización Requerida'),
      idSede : Yup.number().positive().optional()
    }
  )

  return (
    <>
      <Box sx={{p:2}}>
        <form onSubmit={idFormik.handleSubmit} style={{display:'flex'}}>
          <TextField margin='dense' variant='outlined'
            name='id' 
            id='idLocalidad' 
            type='number'
            label='Id Localidad' 
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
        fields={localidadFields}
        initialValues={savedInitialValues.current}
        validationSchema={validationSchema}
        onSubmit={onSubmit}/> 
        : <SekeletonForm formName={pageName} fields={localidadFields} />
      }
    </>
  )
}

export default ModificarLocalidad
export const Head = ()=><title>{pageName}</title>
