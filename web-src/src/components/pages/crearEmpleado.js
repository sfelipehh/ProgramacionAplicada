import * as React from 'react'
import * as Yup from 'yup'
    
import CustomForm from '../customForm'
import {empleadoFields, setterEmpleadoUrl} from '../../data_models/dataModel'
import { isLogged } from '../../services/autentication'

const pageName = "Crear Empleado"

const initialValues = {
  dni : '',
  nombres : '',
  apellidos  : '',
  cargo: '',
  celular : '',
  email : '',
  fechaNacimiento : '', 
  idSede : '',
  cupoAsignado : 0
}

const validationSchema = Yup.object().shape(
  {
    dni : Yup.number().positive().required("Identificación Requerida"),
    nombres : Yup.string().required("Nombres Requeridos"),
    apellidos : Yup.string().required("Apellidos Requeridos"),
    cargo : Yup.string().required("Cargo Requerido") ,
    celular : Yup.string()
    .matches("[0-9]{3}-[0-9]{7}","El celuar de ser xxx-xxxxxxx")
    .required("Celular Requerido"),
    email : Yup.string().email("Correo Invalido (ej@emp.lo)").required("Correo Electronico Requerido"),
    fechaNacimiento : Yup.date().required("Fecha de Nacimiento Requerida"),
    idSede : Yup.number().positive().required("Id de Sede Requerido"),
    cupoAsignado : Yup.number().positive().optional()
  }
)

const CrearEmpleado = ()=> {
  const onSubmit = async (values,actions)=>{
    console.log(JSON.stringify(values,null,2))
    const copy_values = {...values}
    delete copy_values.idSede
    await fetch(
      'http://localhost:8080'+setterEmpleadoUrl,
      {
        method:'POST',
        mode:'cors',
        headers: {
          'Content-Type':'application/json'
        },
        body : JSON.stringify({sede:{id:values.idSede}, ...copy_values},null,2)
      }
    ).then(response =>{
      alert(`Empleado Guardado Id:${response.headers.get("id")}`)
      actions.resetForm()
    }
    ).catch(e=>console.error(e))
  }

  return(
    <>
    {isLogged() ?
    <CustomForm formName={pageName} 
    fields={empleadoFields}
    initialValues={initialValues}
    validationSchema={validationSchema}
    onSubmit={onSubmit}
    />: <></>}
    </>
    )
}

export default CrearEmpleado
export const Head = ()=><title>{pageName}</title>