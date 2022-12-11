import * as React from 'react'
import Box from '@mui/material/Box'
import Skeleton from '@mui/material/Skeleton'

const SekeletonDisplayData = ({fields=[{id:'id'}]})=>{

  return (
    <Box sx={{px:2, flexDirection:'column', width:360, maxWidth:360, display:'flex'}}>
        {fields.map((field,index)=>(
          <Skeleton height={70} width={200} key={field.id+index}>
          </Skeleton>
        ))}
    </Box>
  )
}

export default SekeletonDisplayData