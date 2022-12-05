import * as React from 'react'
import Layout from '../components/layout'
import * as Yup from 'yup'
import CreationForm from '../components/creationForm'

const pageName = "Crear Evento de Gasto"

const fields = [
  { id:'fecha', name:'Fecha', type:'date', required:true},
  { id:'hora', name:'Hora', type:'time', required:true},
  { id:'idEmpleado', name:'Id Empleado', type:'number', required:true},
  { id:'idLocalidad', name:'Id Localidad', type:'number', required:true},
  { id:'idCuadrilla', name:'Id Cuadrilla', type:'number', required:true},
  { id:'descripcion', name:'Descripción', type:'text', required:true},
  { id:'valor', name: 'Valor', type:'number', required:true}
]

const onSubmit = async (values,actions)=>{
  alert(JSON.stringify(values))
}

const validationSchema = Yup.object().shape(
  {
    fecha : Yup.date().required("Fecha Requerida"),
    hora : Yup.string().matches("[0-2][0-9]:[0-5][0-9]").required("Hora Requerida"),
    idEmpleado : Yup.number().required("Id de Empleado Requerido"),
    idLocalidad : Yup.number().required("Id de Localidad Requerido"),
    idCuadrilla : Yup.number().required("Id de Cuadrilla Rquerido"),
    descripcion : Yup.string().trim().min(20,({min})=> `Descripcion Insuficiente minimo ${min} letras`).required("Descripcion Requerida"),
    valor : Yup.number().required("Valor del Gasto Requerido")
  }
)
const initialValues = {
  fecha : '',
  hora : '',
  idEmpleado : '',
  idLocalidad : '',
  idCuadrilla : '',
  descripcion : '',
  valor : ''
}

const CrearEventoGasto = ()=>(
  <Layout pageName={pageName}>
    <CreationForm formName={pageName} 
      fields={fields}
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={onSubmit}
    />
  </Layout>
)

export default CrearEventoGasto

export const Head= () => <title>{pageName}</title>
