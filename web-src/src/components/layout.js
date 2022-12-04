import * as React from 'react'
import { 
  List, 
  ListItemButton, 
  ListItemText, 
  AppBar, 
  Box, 
  Toolbar,
  Typography,
  IconButton,
  CssBaseline,  
  Drawer,
  ListItem,
  ListItemIcon} from '@mui/material'
import { Link } from 'gatsby'
const drawerWidth = '20vw'
const links = [
  ['/','Index'],
  ['/404','404'],
  ['/crearSede','Crear Sede']
]

const Layout = ({pageName,children})=> (
  <>
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar position="fixed" sx={{ zIndex: (theme)=>theme.zIndex.drawer + 1}}>
        <Toolbar variant="dense">
          <Typography variant="h6" color="inherit" noWrap component="div">
            Programación Aplicada {'>'} {pageName}
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
              <ListItemButton href={item[0]} LinkComponent={<Link to={item[0]} />}>
                <ListItemIcon >
                PosibleIcono
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
)

export default Layout
