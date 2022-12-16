 import * as React from 'react';
import { DataGrid, GridToolbarContainer } from '@mui/x-data-grid';
import { Box, Typography } from '@mui/material';

//https://mui.com/material-ui/react-table/#data-table

const columns = [
  { field: 'id', headerName: 'ID', width: 70, sortable:false },
  { field: 'firstName', headerName: 'First name', width: 130, sortable:false },
  { field: 'lastName', headerName: 'Last name', width: 130, sortable:false },
  {
    field: 'age',
    headerName: 'Age',
    type: 'number',
    width: 90, sortable:false
  },
  {
    field: 'fullName',
    headerName: 'Full name',
    description: 'This column has a value getter and is not sortable.',
    sortable: false,
    width: 160,
    valueGetter: (params) =>
      `${params.row.firstName || ''} ${params.row.lastName || ''}`,
  },
];

const rows = [
  { id: 1, lastName: 'Snow', firstName: 'Jon', age: 35 },
  { id: 2, lastName: 'Lannister', firstName: 'Cersei', age: 42 },
  { id: 3, lastName: 'Lannister', firstName: 'Jaime', age: 45 },
  { id: 4, lastName: 'Stark', firstName: 'Arya', age: 16 },
  { id: 5, lastName: 'Targaryen', firstName: 'Daenerys', age: null },
  { id: 6, lastName: 'Melisandre', firstName: null, age: 150 },
  { id: 7, lastName: 'Clifford', firstName: 'Ferrara', age: 44 },
  { id: 8, lastName: 'Frances', firstName: 'Rossini', age: 36 },
  { id: 9, lastName: 'Roxie', firstName: 'Harvey', age: 65 },
];

const columnasStructure = {
  'sede':[
    { field: 'id', headerName: 'ID', width: 70, sortable:false },
    { field: 'nombre', headerName:'Nombre', width:130, sortable:false},
    { field:'direccion', headerName:'Dirección', width :130, sortable:false}
  ],
  'cuadrilla':[
    {field:'id',headerName:'ID',width:70,sortable:false},
    {field:'nombre', headerName:'Nombre', width:130,sortable:false},
    {field:'cupoAsignado', headerName:'Cupo Asignado', width:70, sortable:false},
    {field:'supervisorCuadrilla', headerName:'Supervisor', width:160,sortable:false,
    valueGetter:(params) =>`${
      params.row.supervisorCuadrilla.empleado !== null ? 
      'Id:' + params.row.supervisorCuadrilla.empleado.id : ''} 
      ${params.row.supervisorCuadrilla.empleado !== null ? 
        params.row.supervisorCuadrilla.empleado.nombres : ''}`,}
  ],
  'empleado':[
    {field:'id', headerName:'ID',width:50,sortable:false},
    {field:'dni', headerName:'Identificacion', width:100, sortable:false},
    {field:'nombres', headerName:'Nombres', width:130, sortable:false},
    {field:'apellidos', headerName:'Apellidos', width:130, sortable:false},
    {field:'celular', headerName:'Celular', width:130, sortable:false},
    {field:'email', headerName:'Correo', width:130,sortable:false},
    {field:'fechaNacimiento', headerName:'Fecha de Nacimiento', width:130,sortable:false},
    {field:'cuadrilla', headerName:'Cuadrilla', width:160,sortable:false,
    valueGetter:(params)=>`${params.row.cuadrilla !== null ? 'Id:'+params.row.cuadrilla.id : ''} ${params.row.cuadrilla !== null ? params.row.cuadrilla.nombre : ''}`},
    {field:'sede', headerName:'Sede', width:160, sortable:false,
    valueGetter:(params)=>`${params.row.sede !== null ?'Id:'+params.row.sede.id : ''} ${params.row.sede !==null ? params.row.sede.nombre : ''}`}
  ],
  'localidad':[
    {field:'id', headerName:'ID', width:70, sortable:false},
    {field:'nombre', headerName:'Nombre', width:130, sortable:false},
    {field:'calleInicio', headerName:'Calle Inicio', width:130,sortable:false},
    {field:'calleFin', headerName:'Calle Fin', width:130, sortable:false},
    {field:'carreraInicio',headerName:'Carrera Fin', width:130, sortable:false},
    {field:'carreraFin', headerName:'Carrera Fin', width:130,sortable:false},
    {field:'cuadrilla', headerName:'Cuadrilla', width:160,sortable:false,
    valueGetter:(params)=>`${params.row.cuadrilla !== null ? 'Id:'+params.row.cuadrilla.id : ''} ${params.row.cuadrilla !== null ? params.row.cuadrilla.nombre : ''}`},
    {field:'sede', headerName:'Sede', width:160, sortable:false,
    valueGetter:(params)=>`${params.row.sede !== null ? 'Id:'+params.row.sede.id : ''} ${params.row.sede !==null ? params.row.sede.nombre : ''}`}
  ],
  'eventodegasto':[
    {field:'id', headerName:'ID', width:70, sortable:false},
    {field:'fecha', headerName:'Fecha', width:130, sortable:false},
    {field:'hora', headerName:'Fecha', width:130, sortable:false},
    {field:'valor', headerName:'Valor', width:130, sortable:false},
    {field:'localidad', headerName:'Localidad', width:160, sortable:false,
    valueGetter:(params)=>`${params.row.loading !== null ? 'Id:'+params.row.localidad.id : ''} ${params.row.loading !== null ? params.row.localidad.nombre : ''}`},
    {field:'empleado', headerName:'Empleado', width:160, sortable:false,
    valueGetter:(params)=>`${params.row.empleado !==null ? 'Id:'+params.row.empleado.id : ''} ${params.row.empleado !==null ? 'DNI:'+params.row.empleado.dni : ''} ${params.row.empleado !==null ?  params.row.empleado.nombres : ''}`},
    {field:'cuadrilla', headerName:'Cuadrilla', width:160, sortable:false,
    valueGetter:(params)=>`${params.row.cuadrilla !== null ? 'Id:'+params.row.cuadrilla.id : ''} ${params.row.cuadrilla !== null ? params.row.cuadrilla.nombre : ''}`}
  ]
}

const wrapGetData = (f) => {f()} 

const getData = async (dataGetUrl,stateChangeFunction, dataRef, params)=>{
  await fetch(
    'http://localhost:8080'+dataGetUrl + params,
    {
      method:'GET',
      mode:'cors',
      headers: {
        'Content-Type':'application/json'
      }
    }
  )
  .then(response => {
    if(response.status===302){
      response.json().then(data =>{
        dataRef.current.filas = data
        dataRef.current.columnas = columnasStructure[response.headers.get("type").toLowerCase()] 
        console.log(dataRef.current)
        stateChangeFunction(true)
      })  
    } 
  }).catch(e=>console.log(e))
  
}
const DataToolbar = ({tableTitle,error})=>(
  <GridToolbarContainer>
    <Typography sx={{px:1}} variant='body2'>
      {tableTitle}
    </Typography>
    {error ? <Typography color={(theme)=>theme.palette.error.dark} sx={{px:1}} variant='body2'>{error}</Typography>:''}
  </GridToolbarContainer>
)

export default function DataTable({id,tableTitle, useCheckBox, error, selectionModelFun, dataUrl, tableProps, dataFetchParams}) {
  const [dataLoaded, setDataLoaded] = React.useState(false)
  const dataRef = React.useRef({columnas:columns, filas: rows})
  const [selection, setSelection] = React.useState([])
  return (
    <>
      {dataLoaded ?
      <Box sx={{ height: 300, width: '100%', py:1}}>
        <DataGrid {...tableProps} loading={!dataLoaded} components={{Toolbar:DataToolbar}} componentsProps={{toolbar:{tableTitle,error}}}
          selectionModel={selection} 
          disableColumnMenu
          rows={dataRef.current.filas}
          columns={dataRef.current.columnas}
          pageSize={5}
          rowsPerPageOptions={[5]}
          checkboxSelection={useCheckBox}
          onSelectionModelChange={
            (newModel)=>{
            if(selectionModelFun !== undefined){
              if(useCheckBox){
                selectionModelFun(id, newModel) 
              }else{
                selectionModelFun(id,newModel[0])
              }
            }
            setSelection(newModel)
          }} />
      </Box> : wrapGetData(()=> getData(dataUrl,setDataLoaded, dataRef, dataFetchParams))
    }
    </>
  );
}

