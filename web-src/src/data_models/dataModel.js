//Cargos Urls
export const getAllCargosUrl = '/consultas/getCargos'
export const getterCargoUrl = '/consultas/getCargoById?id='
export const setterCargoUrl = '/registros/setCargo'
//Sedes Urls
export const getAllSedesUrl = '/consultas/getSedes'
export const gettterSedeUrl = '/consultas/getSedeById?id='
export const setterSedeUrl = '/registros/setSede'
//Cuadrilla Urls
export const getAllCuadrillasUrl = '/consultas/getCuadrillas'
export const getterCuadrillaUrl = '/consultas/getCuadrillaById?id='
export const setterCuadrillaUrl = '/registros/setCuadrilla'
export const getCuadrillaByAny = '/getCuadrilla'
//Empleados Urls
export const getAllEmpleadosUrl = '/consultas/getEmpleados'
export const getterEmpleadoUrl = '/consultas/getEmpleadoById?id='
export const setterEmpleadoUrl = '/registros/setEmpleado'
export const getEmpleadoByAnyUrl = '/consultas/getEmpleado'
export const getEmpleadoByAnyParams = {idCuadrilla:"idCuadrilla=",idSede:"idSede="}
//Localidades Urls
export const getAllLocalidadesUrl = '/consultas/getLocalidades'
export const gettterLocalidadUrl = '/consultas/getLocalidadById?id='
export const setterLocalidadUrl = '/registros/setLocalidad'

//Eventos de Gasto Urls
export const getAllEventosGastoUrl = '/consultas/getEventosGasto'
export const getterEventoGastoUrl = '/consultas/getEventoGastoById?id='
export const setterEventoGastoUrl = '/registros/setEventoGasto'
export const getEventoCostoByAnyUrl = '/consultas/getEventoGastoByAny'
export const getEventoCostoByAnyParams = {idCuadrilla:"idCuadrilla=",idEmpleado:"idEmpleado=",aprobado:"aprobado="}

//Login Url
export const doLogin = '/login'


export const sedeFields = [
  { id:'nombre', name: 'Nombre', type:'text', basic: true, foreingSelection:{on:false,url:null}, required: true}, 
  { id:'direccion', name:'Dirección', type:'text', basic:true, foreingSelection:{on:false,url:null}, required: true},
  { id:'idAdministrador', name:'Id Administrador', 
  type:'number', basic:false, foreingSelection:{on:true,url:getEmpleadoByAnyUrl} , required:false}
]

export const cuadrillaFields = [
  { id:'nombre', name: 'Nombre', type:'text', basic: true, foreingSelection:{on:false,url:null},required:true},
  { id:'idSede', name: 'Id Sede', type:'number', basic: true, foreingSelection:{on:true,url:getAllSedesUrl},required:true},
  { id:'idSupervisor', name: 'Id Supervisor', type:'text', basic: false,foreingSelection:{on:false,url:getEmpleadoByAnyUrl},required:true},
  { id:'cantidadEmpleados', name: 'Cantidad de Empleados', type:'text', basic: true, foreingSelection:{on:false,url:null},required:true},
  { id:'cupoAsignado', name: 'Cupo Asignado', type:'text', basic: true, foreingSelection:{on:false,url:null},required:false},
  { id:'empleados', name: 'Empleados', type:'array', basic:false, foreingSelection:{on:true, url:getAllEmpleadosUrl}, required:false},
  { id:'localidades', name:'Localidades',type:'array', basic:false, foreingSelection:{on:true, url:getAllLocalidadesUrl}, required:false}

]

export const empleadoFields = [
  { id:'dni', name :'Identificación', type:'number', basic:true, foreingSelection:{on:false, url:null}, required:true},
  { id:'nombres', name :'Nombres', type:'text', basic:true, foreingSelection:{on:false, url:null}, required:true},
  { id:'apellidos', name :'Apellidos', type:'text', basic:true, foreingSelection:{on:false, url:null}, required:true},
  { id:'celular', name :'Celular', type:'tel', basic:true, foreingSelection:{on:false,url:null}, required:true},
  { id:'email', name :'Correo', type:'text', basic:true, foreingSelection:{on:false, url:null} ,required:true},
  { id:'fechaNacimiento', name :'Fecha de Nacimiento', type:'date', basic:true, foreingSelection:{on:false,url:null},required:true},
  { id:'cargo', name :'Cargo', type:'text', basic:true, foreingSelection:{on:false, url:null}, required:true},
  { id:'idCuadrilla', name :'Id Cuadrilla', type:'number', basic:false, foreingSelection:{on:true,url:getAllCuadrillasUrl},required:false},
  { id:'idSede', name :'Id Sede', type:'number', basic:true, foreingSelection:{on:true,url:getAllSedesUrl},required:true},
  { id:'cupoAsignado', name :'Cupo Asignado', type:'number', basic:true, foreingSelection:{on:false,url:null}, required:false}
]

export const localidadFields = [
  { id:'nombre', name : 'Nombre', type:'text', basic:true, foreingSelection:{on:false,url:null}, required : true},
  { id:'calleInicio', name : 'Calle Inicio', type:'text', basic:true, foreingSelection:{on:false,url:null}, required : true},
  { id:'calleFin', name : 'Calle Fin', type:'text', basic:true, foreingSelection:{on:false,url:null}, required : true},
  { id:'carreraInicio', name : 'Carrera Inicio', type:'text', basic:true, foreingSelection:{on:false,url:null}, required : true},
  { id:'carreraFin', name : 'Carrera Fin', type:'text', basic:true, foreingSelection:{on:false,url:null}, required : true},
  { id:'idSede', name : 'Id Sede', type:'number', basic:true, foreingSelection:{on:true,url:getAllSedesUrl}, required : true}
]

export const eventoDeGastoFields = [
  { id:'fecha', name:'Fecha', type:'date', basic:true, foreingSelection:{on:false,url:null}, required:true},
  { id:'hora', name:'Hora', type:'time', basic:true, foreingSelection:{on:false,url:null}, required:true},
  { id:'idEmpleado', name:'Id Empleado', type:'number', basic:true, foreingSelection:{on:true,url:getAllEmpleadosUrl}, required:true},
  { id:'idLocalidad', name:'Id Localidad', type:'number', basic:true, foreingSelection:{on:true,url:getAllLocalidadesUrl},  required:true},
  { id:'idCuadrilla', name:'Id Cuadrilla', type:'number', basic:true, foreingSelection:{on:true,url:getAllCuadrillasUrl}, required:true},
  { id:'descripcion', name:'Descripción', type:'text', basic:true, foreingSelection:{on:false,url:null}, required:true},
  { id:'valor', name: 'Valor', type:'number', basic:true, foreingSelection:{on:false,url:null}, required:true},
  { id:'aprobado', name:'Aprobado', type:'boolean', basic:false, foreingSelection:{on:false,url:null}, required:true}
]

export const loginFields = [
  {id:'username', name:'Nombre de Usuario', type:'text', basic:true, foreingSelection:{on:false,url:null}, requred:true},
  {id:'password', name:'Contraseña', type:'password', basic:true, foreingSelection:{on:false,url:null}, required:true}
]