import * as React from 'react'
import * as Yup from 'yup'
import Layout from '../components/layout'
import CreationForm from '../components/creationForm'

const pageName = "Crear Empleado"

const fields = [
  { id:'identificacion', name :'Identificación', type:'number', required:true},
  { id:'nombres', name :'Nombres', type:'text', required:true},
  { id:'apellidos', name :'Apellidos', type:'text', required:true},
  { id:'cargo', name :'Cargo', type:'text', required:true},
  { id:'celular', name :'Celular', type:'tel', required:true},
  { id:'correo', name :'Correo', type:'text', required:true},
  { id:'fechaNacimiento', name :'Fecha de Nacimiento', type:'date', required:true},
  { id:'idCuadrilla', name :'Id Cuadrilla', type:'number', required:false},
  { id:'idSede', name :'Id Sede', type:'number', required:true},
  { id:'cupoAsignado', name :'Cupo Asignado', type:'number', required:false}
]

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
    identificacion : Yup.number().required("Identificación Requerida"),
    nombres : Yup.string().required("Nombres Requeridos"),
    apellidos : Yup.string().required("Apellidos Requeridos"),
    cargo : Yup.string().required("Cargo Requerido") ,
    celular : Yup.string()
    .matches("[0-9]{3}-[0-9]{7}","El celuar de ser xxx-xxxxxxx")
    .required("Celular Requerido"),
    correo : Yup.string().email("Correo Invalido (ej@emp.lo)").required("Correo Electronico Requerido"),
    fechaNacimiento : Yup.date().required("Fecha de Nacimiento Requerida"),
    idCuadrilla : Yup.number().optional(),
    idSede : Yup.number().required("Id de Sede Requerido"),
    cupoAsignado : Yup.number().optional()
  }
)

const CrearEmpleado = ()=> (
  <Layout pageName={pageName} >
    <CreationForm formName={pageName} 
    fields={fields}
    initialValues={initialValues}
    validationSchema={validationSchema}
    onSubmit={onSubmit}
    />
  </Layout>
)

export default CrearEmpleado
export const Head = ()=><title>{pageName}</title>