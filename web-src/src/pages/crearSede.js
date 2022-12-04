import * as React from 'react'
import Layout from '../components/layout'
import * as Yup from 'yup'
import CreationForm from '../components/creationForm'

const fields = ['direccion','administrador']

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
    direccion : Yup.string().required("Dirección Obligatoria"),
    administrador : Yup.number().optional()
  }
)
const initialValues = {
  direccion : '',
  administrador : ''
}

const CrearSede = ()=>(
  <Layout pageName='Crear Sede'>
    <CreationForm formName='Crear Sede' 
      fields={fields}
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={onSubmit}
    /> 
  </Layout>
)

export default CrearSede

export const Head= () => <title>Crear Sede</title>