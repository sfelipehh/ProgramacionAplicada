import * as React from 'react'
import { List, ListItem, ListItemText } from '@mui/material'
import { Box } from '@mui/system'


const DisplayData = ({fields, data})=>{
  console.log(data)
  return(
    <Box sx={{px:2, width:360, maxWidth:360, display:'flex',justifyContent:'flex-start'}}>
      <List>
        {fields.map((field,index)=>(
          <ListItem key={field.id+index} disablePadding>
            <ListItemText primary={field.name} secondary={data[field.id]} />
          </ListItem>
        ))}
      </List>
    </Box>
  )
}

export default DisplayData