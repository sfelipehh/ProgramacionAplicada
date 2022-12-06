//Cargos Urls
export const getAllCargosUrl = '/getCargos'
export const getterCargoUrl = '/getCargoById'
export const setterCargoUrl = '/setCargo'
//Sedes Urls
export const getAllSedesUrl = '/getSedes'
export const gettterSedeUrl = '/getSedeById'
export const setterSedeUrl = '/setSede'
//Cuadrilla Urls
export const getAllCuadrillasUrl = '/getCuadrillas'
export const getterCuadrillaUrl = '/getCuadrillaById'
export const setterCuadrillaUrl = '/setCuadrilla'
export const getCuadrillaByAny = '/getCuadrilla'
//Empleados Urls
export const getAllEmpleadosUrl = '/getEmpleados'
export const getterEmpleadoUrl = '/getEmpleadoById'
export const setterEmpleadoUrl = '/setEmpleado'
export const getEmpleadoByAnyUrl = '/getEmpleado'
//Localidades Urls
export const getAllLocalidadesUrl = '/getLocalidades'
export const gettterLocalidadUrl = '/getLocalidadById'
export const setterLocalidadUrl = '/setLocalidad'

export const cargoFields =  [
  { id:'nombre', name:'Nombre', type:'text', foreingSelection:{on:false,url:null}, required:true}
]

export const sedeFields = [
  { id:'nombre', name: 'Nombre', type:'text', foreingSelection:{on:false,url:null}, required: true}, 
  { id:'direccion', name:'Dirección', type:'text', foreingSelection:{on:false,url:null}, required: true},
  { id:'idAdministrador', name:'Id Administrador', 
  type:'number',foreingSelection:{on:true,url:getEmpleadoByAnyUrl} , required:false}
]

export const cuadrillaFields = [
  { id:'nombre', name: 'Nombre', foreingSelection:{on:false,url:null},required:true},
  { id:'idSede', name: 'Id Sede', foreingSelection:{on:true,url:getAllSedesUrl},required:true},
  { id:'idSupervisor', name: 'Id Supervisor', foreingSelection:{on:false,url:getEmpleadoByAnyUrl},required:true},
  { id:'cantidadEmpleados', name: 'Cantidad de Empleados', foreingSelection:{on:false,url:null},required:true},
  { id:'cupoAsignado', name: 'Cupo Asignado', foreingSelection:{on:false,url:null},required:false}
]

export const empleadoFields = [
  { id:'identificacion', name :'Identificación', type:'number', externalforeingSelection:{on:false, url:null}, required:true},
  { id:'nombres', name :'Nombres', type:'text',foreingSelection:{on:false, url:null}, required:true},
  { id:'apellidos', name :'Apellidos', type:'text', foreingSelection:{on:false, url:null}, required:true},
  { id:'cargo', name :'Cargo', type:'text', foreingSelection:{on:true, url:getAllCargosUrl}, required:true},
  { id:'celular', name :'Celular', type:'tel',foreingSelection:{on:false,url:null}, required:true},
  { id:'correo', name :'Correo', type:'text', foreingSelection:{on:false, url:null} ,required:true},
  { id:'fechaNacimiento', name :'Fecha de Nacimiento', type:'date', foreingSelection:{on:false,url:null},required:true},
  { id:'idCuadrilla', name :'Id Cuadrilla', type:'number', foreingSelection:{on:true,url:getAllCuadrillasUrl},required:false},
  { id:'idSede', name :'Id Sede', type:'number', foreingSelection:{on:true,url:getAllSedesUrl},required:true},
  { id:'cupoAsignado', name :'Cupo Asignado', type:'number', foreingSelection:{on:false,url:null}, required:false}
]

export const localidadFields = [
  { id:'nombre', name : 'Nombre', foreingSelection:{on:false,url:null}, required : true},
  { id:'calleInicio', name : 'Calle Inicio', foreingSelection:{on:false,url:null}, required : true},
  { id:'calleFin', name : 'Calle Fin', foreingSelection:{on:false,url:null}, required : true},
  { id:'carreraInicio', name : 'Carrera Inicio', foreingSelection:{on:false,url:null}, required : true},
  { id:'carreraFin', name : 'Carrera Fin', foreingSelection:{on:false,url:null}, required : true},
  { id:'idSede', name : 'Id Sede', foreingSelection:{on:true,url:getAllSedesUrl}, required : true}
]