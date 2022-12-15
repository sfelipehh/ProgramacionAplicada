import * as React from 'react'
    
import * as Yup from 'yup'
import CustomForm from '../customForm'
import { sedeFields, setterSedeUrl } from '../../data_models/dataModel'
import { isLogged } from '../../services/autentication'

const pageName = "Crear Sede"



const validationSchema = Yup.object().shape(
  {
    nombre : Yup.string().required("Nombre de Sede Obligatorio"),
    direccion : Yup.string().required("Dirección Obligatoria")
  }
)
const initialValues = {
  nombre : '',
  direccion : '',
}

const CrearSede = ()=>{
  const onSubmit = async (values,actions)=>{
    console.log(JSON.stringify(values,null,2))
    await fetch(
      'http://localhost:8080'+setterSedeUrl,
      {
        method:'POST',
        mode:'cors',
        headers: {
          'Content-Type':'application/json'
        },
        body : JSON.stringify(values,null,2)
      }
    ).then(response =>{
      alert(`Sede Creada Id: ${response.headers.get("id")}`)
      actions.resetForm()
    }
    ).catch(e=>console.error(e))
  }
  return (
    <>
    {isLogged() ? 
    <CustomForm formName={pageName} 
      fields={sedeFields}
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={onSubmit}
      /> 
      :  <></>}
    </>
  )
}

export default CrearSede

export const Head= () => <title>Crear Sede</title>