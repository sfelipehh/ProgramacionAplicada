import * as React from 'react'
import * as Yup from 'yup'
    
import CustomForm from '../customForm'
import {cuadrillaFields} from '../../data_models/dataModel'

const pageName = "Crear Cuadrilla"


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
    idSede : Yup.number().positive().required('Id de Sede Requerido'),
    idSupervisor : Yup.number().positive().required('Id de Empleado Supervisor Requerido'),
    cantidadEmpleados : Yup.number().positive().required('Cantidad de Empleados Requerida'),
    cupoAsignado : Yup.number().positive().optional()
  }
)

const CrearCuadrilla = ()=>(
  <>
    <CustomForm  formName={pageName}
    fields={cuadrillaFields}
    initialValues={initialValues}
    validationSchema={validationSchema}
    onSubmit = {onSubmit}
    />
  </>

)

export default CrearCuadrilla
export const Head = ()=><title>{pageName}</title>