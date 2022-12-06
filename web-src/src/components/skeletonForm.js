import * as React from 'react'
import Box from '@mui/material/Box'
import Skeleton from '@mui/material/Skeleton'
import Button from '@mui/material/Button'

const SekeletonForm = ({formName, fields, width, height}) => (

  <Box sx={{
    width : width || 'auto',
    height: height || 'auto',
    padding: 2
    }}>
      <form style={{width:'100%', height:'100%' }} >
      {
        fields.map((field,index)=>(
          <Skeleton key={index} 
           sx={{ my:2 }} 
           variant='rounded' 
           width='100%' 
           height={70} />
        ))
      }
      <Button disabled color='primary' variant='contained' fullWidth>
        {formName}
      </Button>
      </form>
      
    </Box>
)

export default SekeletonForm