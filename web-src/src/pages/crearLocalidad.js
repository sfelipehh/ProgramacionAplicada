import * as React from 'react'
import * as Yup from 'yup'
import Layout from '../components/layout'
import CreationForm from '../components/creationForm'

const pageName = "Crear Localidad"

const fields = [
  'nombre',
  'calleInicio',
  'calleFin',
  'carreraInicio',
  'carreraFin',
  'idSede']

const onSubmit = (values,actions)=>{
  alert(JSON.stringify(values))
}

const initialValues = {
  nombre : '',
  calleInicio : '',
  calleFin : '',
  carreraInicio : '',
  carreraFin : '',
  idSede : ''
}

const validationSchema = Yup.object().shape(
  {
    nombre : Yup.string().required('Nombre de Localiad Requerido'),
    calleInicio : Yup.string().required('Calle de Inicio Requerida'),
    calleFin : Yup.string().required('Calle de Finalización Requerida'),
    carreraInicio : Yup.string().required('Carreda de Inicio Requerida'),
    idSede : Yup.number().optional()
  }
)

const CrearLocalidad = ()=> (
  <Layout pageName={pageName} >
    <CreationForm formName={pageName} 
    fields={fields}
    initialValues={initialValues}
    validationSchema={validationSchema}
    onSubmit={onSubmit}
    />
  </Layout>
)

export default CrearLocalidad
export const Head = ()=><title>{pageName}</title>