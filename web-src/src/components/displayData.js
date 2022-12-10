import { List, ListItem, ListItemText } from '@mui/material'
import { Box } from '@mui/system'
import * as React from 'react'


const DisplayData = ({fields, data})=>{
  console.log(data)
  return(
    <Box sx={{px:2, maxWidth:360}}>
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