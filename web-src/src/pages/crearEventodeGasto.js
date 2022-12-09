import * as React from 'react'
import Layout from '../components/layout'
import * as Yup from 'yup'
import CustomForm from '../components/customForm'
import { eventoDeGastoFields } from '../data_models/dataModel'

const pageName = "Crear Evento de Gasto"

const onSubmit = async (values,actions)=>{
  alert(JSON.stringify(values))
}

const validationSchema = Yup.object().shape(
  {
    fecha : Yup.date().required("Fecha Requerida"),
    hora : Yup.string().matches("[0-2][0-9]:[0-5][0-9]").required("Hora Requerida"),
    idEmpleado : Yup.number().positive().required("Id de Empleado Requerido"),
    idLocalidad : Yup.number().positive().required("Id de Localidad Requerido"),
    idCuadrilla : Yup.number().positive().required("Id de Cuadrilla Rquerido"),
    descripcion : Yup.string().trim().min(20,({min})=> `Descripcion Insuficiente minimo ${min} letras`).required("Descripcion Requerida"),
    valor : Yup.number().positive().required("Valor del Gasto Requerido")
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
    <CustomForm formName={pageName} 
      fields={eventoDeGastoFields}
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={onSubmit}
    />
  </Layout>
)

export default CrearEventoGasto

export const Head= () => <title>{pageName}</title>
