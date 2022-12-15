import * as React from 'react'
import * as Yup from 'yup'
    
import CustomForm from '../customForm'
import {cuadrillaFields, setterCuadrillaUrl} from '../../data_models/dataModel'
import { isLogged } from '../../services/autentication'

const pageName = "Crear Cuadrilla"

const initialValues = {
  nombre : '',
  idSede : '',
  cantidadEmpleados : 1,
  cupoAsignado : 0
}

const validationSchema = Yup.object().shape(
  {
    nombre : Yup.string().required('Nombre de Cuadrilla Requerido'),
    idSede : Yup.number().positive().required('Id de Sede Requerido'),
    cantidadEmpleados : Yup.number().positive().required('Cantidad de Empleados Requerida'),
    cupoAsignado : Yup.number().positive().optional()
  }
)

const CrearCuadrilla = ()=>{
  const onSubmit = async (values,actions) => {
    console.log(JSON.stringify(values,null,2))
    const copy_values = {...values}
    delete copy_values.idSede
    await fetch(
      'http://localhost:8080'+setterCuadrillaUrl,
      {
        method:'POST',
        mode:'cors',
        headers: {
          'Content-Type':'application/json'
        },
        body : JSON.stringify({sede:{id:values.idSede}, ...copy_values},null,2)
      }
    ).then(response =>{
      alert(`Cuadrilla Guardada Id:${response.headers.get("id")}`)
      actions.resetForm()
      console.log(response.headers.get("id"))
    }
    ).catch(e=>console.error(e))
  }
   return(
   <>
   {  isLogged() ?
    <CustomForm  formName={pageName}
    fields={cuadrillaFields}
    initialValues={initialValues}
    validationSchema={validationSchema}
    onSubmit = {onSubmit}
    /> : <></>}
  </>
  )
}

export default CrearCuadrilla
export const Head = ()=><title>{pageName}</title>