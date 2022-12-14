//Cargos Urls
export const getAllCargosUrl = '/getCargos'
export const getterCargoUrl = '/getCargoById'
export const setterCargoUrl = '/registros/setCargo'
//Sedes Urls
export const getAllSedesUrl = '/getSedes'
export const gettterSedeUrl = '/getSedeById'
export const setterSedeUrl = '/registros/setSede'
//Cuadrilla Urls
export const getAllCuadrillasUrl = '/getCuadrillas'
export const getterCuadrillaUrl = '/getCuadrillaById'
export const setterCuadrillaUrl = '/registros/setCuadrilla'
export const getCuadrillaByAny = '/getCuadrilla'
//Empleados Urls
export const getAllEmpleadosUrl = '/getEmpleados'
export const getterEmpleadoUrl = '/getEmpleadoById'
export const setterEmpleadoUrl = '/registros/setEmpleado'
export const getEmpleadoByAnyUrl = '/getEmpleado'
//Localidades Urls
export const getAllLocalidadesUrl = '/getLocalidades'
export const gettterLocalidadUrl = '/getLocalidadById'
export const setterLocalidadUrl = '/registros/setLocalidad'

//Eventos de Gasto Urls
export const getAllEventosGastoUrl = '/getEventosGasto'
export const getterEventoGastoUrl = '/getEventoGastoById'
export const setterEventoGastoUrl = '/registros/setEventoGasto'
export const getEventoCostoByAnyUrl = '/getEventoGasto'

//Login Url
export const doLogin = '/login'

export const cargoFields =  [
  { id:'nombre', name:'Nombre', type:'text', basic:true,foreingSelection:{on:false,url:null}, required:true}
]

export const sedeFields = [
  { id:'nombre', name: 'Nombre', type:'text', basic: true, foreingSelection:{on:false,url:null}, required: true}, 
  { id:'direccion', name:'Dirección', type:'text', basic:true, foreingSelection:{on:false,url:null}, required: true},
  { id:'administrador_id', name:'Id Administrador', 
  type:'number', basic:false, foreingSelection:{on:true,url:getEmpleadoByAnyUrl} , required:false}
]

export const cuadrillaFields = [
  { id:'nombre', name: 'Nombre', type:'text', basic: true, foreingSelection:{on:false,url:null},required:true},
  { id:'idSede', name: 'Id Sede', type:'number', basic: true, foreingSelection:{on:true,url:getAllSedesUrl},required:true},
  { id:'idSupervisor', name: 'Id Supervisor', type:'text', basic: true,foreingSelection:{on:false,url:getEmpleadoByAnyUrl},required:true},
  { id:'cantidadEmpleados', name: 'Cantidad de Empleados', type:'text', basic: true, foreingSelection:{on:false,url:null},required:true},
  { id:'cupoAsignado', name: 'Cupo Asignado', type:'text', basic: true, foreingSelection:{on:false,url:null},required:false},
  { id:'empleados', name: 'Empleados', type:'array', basic:false, foreingSelection:{on:true, url:getAllEmpleadosUrl}, required:false},
  { id:'localidades', name:'Localidades',type:'array', basic:false, foreingSelection:{on:true, url:getAllLocalidadesUrl}, required:false}

]

export const empleadoFields = [
  { id:'dni', name :'Identificación', type:'number', basic:true, foreingSelection:{on:false, url:null}, required:true},
  { id:'nombres', name :'Nombres', type:'text', basic:true, foreingSelection:{on:false, url:null}, required:true},
  { id:'apellidos', name :'Apellidos', type:'text', basic:true, foreingSelection:{on:false, url:null}, required:true},
  { id:'cargo', name :'Cargo', type:'text', basic:true, foreingSelection:{on:true, url:getAllCargosUrl}, required:true},
  { id:'celular', name :'Celular', type:'tel', basic:true, foreingSelection:{on:false,url:null}, required:true},
  { id:'correo', name :'Correo', type:'text', basic:true, foreingSelection:{on:false, url:null} ,required:true},
  { id:'fechaNacimiento', name :'Fecha de Nacimiento', type:'date', basic:true, foreingSelection:{on:false,url:null},required:true},
  { id:'idCuadrilla', name :'Id Cuadrilla', type:'number', basic:true, foreingSelection:{on:true,url:getAllCuadrillasUrl},required:false},
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