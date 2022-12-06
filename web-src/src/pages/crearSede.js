import * as React from 'react'
import Layout from '../components/layout'
import * as Yup from 'yup'
import CreationForm from '../components/creationForm'
import { sedeFields } from '../data_models/dataModel'

const pageName = "Crear Sede"

const onSubmit = async (values,actions)=>{
  console.log(JSON.stringify(values,null,2))
  await fetch(
    'http://localhost:8080/demo/add',
    {
      method:'POST',
      mode:'cors',
      headers: {
        'Content-Type':'application/json'
      },
      body : JSON.stringify(values,null,2)
    }
  ).then(value =>
    console.log(value.status)
  )
}

const validationSchema = Yup.object().shape(
  {
    nombre : Yup.string().required("Nombre de Sede Obligatorio"),
    direccion : Yup.string().required("Dirección Obligatoria"),
    idAdministrador : Yup.number().optional()
  }
)
const initialValues = {
  nombre : '',
  direccion : '',
  idAdministrador : ''
}

const CrearSede = ()=>(
  <Layout pageName={pageName}>
    <CreationForm formName={pageName} 
      fields={sedeFields}
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={onSubmit}
    />
  </Layout>
)

export default CrearSede

export const Head= () => <title>Crear Sede</title>