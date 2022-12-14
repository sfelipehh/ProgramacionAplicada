import * as React from 'react'
import { 
  List, 
  ListItemButton, 
  ListItemText, 
  AppBar, 
  Box, 
  Toolbar,
  Typography,
  CssBaseline,  
  Drawer,
  ListItem,
  ListItemIcon } from '@mui/material'
import { Link } from 'gatsby'
import { DomainAdd, 
  AddLocationAlt, 
  GroupAdd,
  AddShoppingCart, 
  Domain,
  EditLocationAlt,
  PeopleAlt,
  PersonAddAlt1,
  Person,
  Login} from '@mui/icons-material'
import pageNames from '../data_models/pageNames'
const drawerWidth = '20vw'
const links = [
  ['/','Index'],
  ['/404','404'],
  ['/crearSede','Crear Sede', <DomainAdd />],
  ['/crearLocalidad','Crear Localidad', <AddLocationAlt />],
  ['/crearCuadrilla', 'Crear Cuadrilla', <GroupAdd />],
  ['/crearEmpleado', 'Crear Empleado', <PersonAddAlt1 />],
  ['/crearEventodeGasto', 'Crear Evento de Gasto', <AddShoppingCart />],
  ['/modificarSede', 'Modificar Sede', <Domain />],
  ['/modificarLocalidad', 'Modificar Localidad', <EditLocationAlt />],
  ['/modificarCuadrilla', 'Modificar Cuadrilla', <PeopleAlt />],
  ['/modificarEmpleado', 'Modificar Empleado', <Person />],
  ['/consultarEmpleado', 'Consultar Empleado', <Person />],
  ['/login', 'Iniciar Sesión', <Login />]
]

const Layout = ({children,params})=> {
  const getPageName = (params)=>{
    const location = params['*']
    return pageNames[location]
  }

  return(
  params['*'] !== 'login' ? 
  <>
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar position="fixed" sx={{ zIndex: (theme)=>theme.zIndex.drawer + 1}}>
        <Toolbar variant="dense">
          <Typography variant="h5" color="inherit" noWrap component="div">
            Programación Aplicada {'>'} {getPageName(params)}
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer variant='permanent'
        sx ={{
          width : drawerWidth,
          height:'auto',
          flexShrink:0,
          [`&.MuiDrawer-paper`] : {width : drawerWidth,height:'auto', boxSizing : 'border-box'}
        }}
      >
        <Toolbar />
        <Box sx={{overflow:'auto', width:drawerWidth}}>
          <List>
            {links.map((item,index)=>(
            <ListItem key={item[1]+index} disablePadding>
              <ListItemButton href={item[0]} to={item[0]} LinkComponent={Link}>
                <ListItemIcon>
                  {item[2]}
                </ListItemIcon>
                <ListItemText primary={item[1]} /> 
              </ListItemButton>
            </ListItem>
            ))}
          </List>
        </Box>
      </Drawer>
      
      <Box component='main' sx={{flexGrow:1, p:3}}>
        <Toolbar variant='dense'/>  
        {children}
      </Box>
    </Box>  
  </>
  : <Box sx={{width:'100%', height:'100%', display:'flex', justifyContent:'center', alignItems:'center'}}>
    {children}
  </Box>
  )
}

export default Layout
