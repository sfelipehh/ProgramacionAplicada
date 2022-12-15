import * as React from 'react'
    
import * as Yup from 'yup'
import CustomForm from '../customForm'
import { eventoDeGastoFields, setterEventoGastoUrl } from '../../data_models/dataModel'
import { isLogged } from '../../services/autentication'

const pageName = "Crear Evento de Gasto"

const onSubmit = async (values,actions)=>{
  console.log(JSON.stringify(values, null,2))
  const copy_values = {...values}
  delete copy_values.idCuadrilla
  delete copy_values.idEmpleado
  delete copy_values.idLocalidad
  await fetch(
    'http://localhost:8080' + setterEventoGastoUrl,
    {
      method:'POST',
      mode:'cors',
      headers : {
        'Content-Type':'application/json'
      },
      body : JSON.stringify({
        cuadrilla:{id:values.idCuadrilla},
        empleado:{id:values.idEmpleado},
        localidad:{id:values.idLocalidad},
        aprobado:false,
        ...copy_values
      })
    }
  ).then(response =>{
    alert(`Evento de Gasto Guardado Id:${response.headers.get("id")}`)
    actions.resetForm()
  }).catch(e=>console.error(e))
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
  <>{ isLogged() ?
    <CustomForm formName={pageName} 
      fields={eventoDeGastoFields}
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={onSubmit}
    /> : <></>
  }
  </>
)

export default CrearEventoGasto

export const Head= () => <title>{pageName}</title>
