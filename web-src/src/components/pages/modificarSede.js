import * as React from 'react'
import * as Yup from 'yup'
import CustomForm from '../customForm'
import SekeletonForm from '../skeletonForm'
import { IconButton, TextField, Box } from '@mui/material'
import { sedeFields } from '../../data_models/dataModel'
import { useFormik } from 'formik'
import { Search } from '@mui/icons-material'
const pageName = "Modificar Sede"

const loadInfo = (stateChangeFunction,initialSaveRef) => {
  setTimeout(() => {
    stateChangeFunction(true)
  }, 500)
  console.log('Load Info')
  initialSaveRef.current = {
    nombre : 'Sede1',
    direccion : 'Calle 100',
    idAdministrador : '10'
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

const ModificarSede = () => {

  const [infoLoaded,changeLoaded] = React.useState(false)
  const savedInitialValues = React.useRef(null)
  const idFormik = useFormik({
    initialValues : {id:''},
    onSubmit : ()=> loadInfo(changeLoaded,savedInitialValues),
    validationSchema : Yup.object().shape({id:Yup.number().positive().required("Ingresa un Id")})
  })
  const validationSchema = Yup.object().shape(
    {
      nombre : Yup.string().required("Nombre de Sede Obligatorio"),
      direccion : Yup.string().required("Dirección Obligatoria"),
      idAdministrador : Yup.number().positive().required("Administrador Requerido")
    }
  )
  const onSubmit = async (values,actions)=>{
    const id = idFormik.values.id
    const data = {id:id, ...values}
    alert(JSON.stringify(data,null,2))
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
  return (
    <>
      <Box sx={{p:2}}>
        <form onSubmit={idFormik.handleSubmit} style={{display:'flex'}}>
          <TextField margin='dense' variant='outlined'
            name='id' 
            id='idSede' 
            type='number'
            label='Id Sede' 
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
        fields={sedeFields}
        initialValues={savedInitialValues.current}
        validationSchema={validationSchema}
        onSubmit={onSubmit}/> 
        : <SekeletonForm formName={pageName} fields={sedeFields} />
      }
    </>
  )
}

export default ModificarSede
export const Head = ()=><title>{pageName}</title>