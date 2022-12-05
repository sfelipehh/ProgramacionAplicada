import * as React from 'react'
import * as Yup from 'yup'
import Layout from '../components/layout'
import CreationForm from '../components/creationForm'

const pageName = "Crear Cuadrilla"

const fields = [
  { id:'nombre', name: 'Nombre', required:true},
  { id:'idSede', name: 'Id Sede', required:true},
  { id:'idSupervisor', name: 'Id Supervisor', required:true},
  { id:'cantidadEmpleados', name: 'Cantidad de Empleados', required:true},
  { id:'cupoAsignado', name: 'Cupo Asignado', required:false}
]

const onSubmit = (values,actions) => {
  alert(JSON.stringify(values,null,2))
}

const initialValues = {
  nombre : '',
  idSede : '',
  idSupervisor : '',
  cantidadEmpleados : 1,
  cupoAsignado : 0
}

const validationSchema = Yup.object().shape(
  {
    nombre : Yup.string().required('Nombre de Cuadrilla Requerido'),
    idSede : Yup.number().required('Id de Sede Requerido'),
    idSupervisor : Yup.number().required('Id de Empleado Supervisor Requerido'),
    cantidadEmpleados : Yup.number().required('Cantidad de Empleados Requerida'),
    cupoAsignado : Yup.number().optional()
  }
)

const CrearCuadrilla = ()=>(
  <Layout pageName={pageName}>
    <CreationForm  formName={pageName}
    fields={fields}
    initialValues={initialValues}
    validationSchema={validationSchema}
    onSubmit = {onSubmit}
    />
  </Layout>

)

export default CrearCuadrilla
export const Head = ()=><title>{pageName}</title>