import * as React from 'react'
import * as Yup from 'yup'
import CustomForm from '../customForm'
import SekeletonForm from '../skeletonForm'
import { IconButton, TextField, Box } from '@mui/material'
import { gettterSedeUrl, sedeFields } from '../../data_models/dataModel'
import { useFormik } from 'formik'
import { Search } from '@mui/icons-material'
const pageName = "Modificar Sede"



const ModificarSede = () => {
  const loadInfo = async (values,actions) => {
    await fetch(
      'http://localhost:8080'+gettterSedeUrl+values.id,
      {
        method:'GET',
        mode:'cors',
        headers: {
          'Content-Type':'application/json'
        }
      }
    ).then(response => response.json()
    ).then(data =>{
      console.log(data)
      changeLoaded(true)
      savedInitialValues.current = data
      }
    )
  }
  
  const [infoLoaded,changeLoaded] = React.useState(false)
  const savedInitialValues = React.useRef(null)
  const idFormik = useFormik({
    initialValues : {id:''},
    onSubmit : loadInfo,
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
    const copy_values = {...values}
    delete copy_values.idAdministrador
    const data = {id:id,  administradorSede:{empleado:{id:values.idAdministrador}} ,...values}
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
      <CustomForm other={{dataFetchParams:idFormik.values.id}}  formName={pageName} formType='modify'
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