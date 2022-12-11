import * as React from "react"
import { Router } from "@reach/router"
import IndexPage from '../components/pages/index'
import CrearSede from '../components/pages/crearSede'
import CrearLocalidad from '../components/pages/crearLocalidad'
import CrearCuadrilla from '../components/pages/crearCuadrilla'
import CrearEmpleado from '../components/pages/crearEmpleado'
import CrearEventoGasto from '../components/pages/crearEventodeGasto'
import ModificarSede from '../components/pages/modificarSede'
import ModificarLocalidad from '../components/pages/modificarLocalidad'
import ModificarCuadrilla from '../components/pages/modificarCuadrilla'
import ModificarEmpleado from '../components/pages/modificarEmpleado'
import ConsultarEmpleado from '../components/pages/consultarEmpleado'
import Login from '../components/pages/login'
import pageNames from '../data_models/pageNames'
const App = () => {
  return (
    <Router basepath='/'>
      <IndexPage path='/' />
      <CrearSede path='/crearSede' />
      <CrearLocalidad path='/crearLocalidad' />
      <CrearCuadrilla path='/crearCuadrilla' />
      <CrearEmpleado path='/crearEmpleado' />
      <CrearEventoGasto path='/crearEventodeGasto' />
      <ModificarSede path='/modificarSede' />
      <ModificarLocalidad path='/modificarLocalidad' />
      <ModificarCuadrilla path='/modificarCuadrilla' />
      <ModificarEmpleado path='/modificarEmpleado' />
      <ConsultarEmpleado path='/consultarEmpleado' />
      <Login path='/login' />
    </Router>
  )
}

export default App
export const Head = ({params})=>{
  const pageTitle = (params)=>{
    const location = params['*']
    return pageNames[location]
  }
  return (
  <title>{pageTitle(params)}</title>
  )
}