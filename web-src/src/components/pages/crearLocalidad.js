import * as React from 'react'
import * as Yup from 'yup'
    
import CustomForm from '../customForm'
import { localidadFields, setterLocalidadUrl } from '../../data_models/dataModel'
import { isLogged } from '../../services/autentication'
const pageName = "Crear Localidad"



const onSubmit = async (values,actions)=>{
  console.log(JSON.stringify(values,null,2))
    const copy_values = {...values}
    delete copy_values.idSede
  await fetch(
  'http://localhost:8080'+setterLocalidadUrl,
      {
        method:'POST',
        mode:'cors',
        headers: {
          'Content-Type':'application/json'
        },
        body : JSON.stringify({sede:{id:values.idSede}, ...copy_values},null,2)
      }
    ).then(response =>{
      alert(`Localidad Guardada Id:${response.headers.get("id")}`)
      actions.resetForm()
    }
    ).catch(e=>console.error(e))
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
    idSede : Yup.number().positive().required()
  }
)

const CrearLocalidad = ()=> (
    <>
    {isLogged() ?
    <CustomForm formName={pageName} 
    fields={localidadFields}
    initialValues={initialValues}
    validationSchema={validationSchema}
    onSubmit={onSubmit}
    /> : <></>
    }
    </>
)

export default CrearLocalidad
export const Head = ()=><title>{pageName}</title>