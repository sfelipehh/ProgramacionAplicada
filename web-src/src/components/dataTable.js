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

const getData = async (dataGetUrl,stateChangeFunction)=>{
  setTimeout(() => {
    stateChangeFunction(true)
  }, 500)
  /*
  let tableData
  await fetch(
    dataGetUrl,
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
  return tableData
  */
}
const DataToolbar = ({tableTitle,error})=>(
  <GridToolbarContainer>
    <Typography sx={{px:1}} variant='body2'>
      {tableTitle}
    </Typography>
    {error ? <Typography color={(theme)=>theme.palette.error.dark} sx={{px:1}} variant='body2'>{error}</Typography>:''}
  </GridToolbarContainer>
)
//selectionModelRef para usar formik.fields.['algo']

export default function DataTable({tableTitle,useCheckBox,error, selectionModelRef, dataUrl}) {
  const [dataLoaded, setDataLoaded] = React.useState(false)
  const [selection, setSelection] = React.useState([1])
  getData('url',setDataLoaded)

  return (
    <>
      {
      <Box sx={{ height: 300, width: '100%', py:1}}>
        <DataGrid loading={!dataLoaded} components={{Toolbar:DataToolbar}} componentsProps={{toolbar:{tableTitle,error}}}
          selectionModel={selection} 
          disableColumnMenu
          rows={rows}
          columns={columns}
          pageSize={5}
          rowsPerPageOptions={[5]}
          checkboxSelection={useCheckBox}
          onSelectionModelChange={
            (newModel)=>{
            /*if(selectionModelRef !== undefined){
              if(useCheckBox){
                selectionModelRef = newModel
              }else{
                selectionModelRef = newModel[0]
              }
            }*/
            setSelection(newModel)
            console.log(newModel)
          }} />
      </Box>
    }
    </>
  );
}

