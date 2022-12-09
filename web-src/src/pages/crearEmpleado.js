import * as React from 'react'
import * as Yup from 'yup'
import Layout from '../components/layout'
import CustomForm from '../components/customForm'
import {empleadoFields} from '../data_models/dataModel'

const pageName = "Crear Empleado"

const onSubmit = (values,actions)=>{
  alert(JSON.stringify(values))
}

const initialValues = {
  identificacion : '',
  nombres : '',
  apellidos  : '',
  cargo: '',
  celular : '',
  correo : '',
  fechaNacimiento : '',
  idCuadrilla : '', 
  idSede : '',
  cupoAsignado : 0
}

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

const CrearEmpleado = ()=> (
  <Layout pageName={pageName} >
    <CustomForm formName={pageName} 
    fields={empleadoFields}
    initialValues={initialValues}
    validationSchema={validationSchema}
    onSubmit={onSubmit}
    />
  </Layout>
)

export default CrearEmpleado
export const Head = ()=><title>{pageName}</title>