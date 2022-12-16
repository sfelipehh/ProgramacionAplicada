import { Typography } from '@mui/material'
import * as React from "react"
    

const IndexPage = () => {
  return (
    <>
    <Typography variant='h3'>
      Proyecto Programación Aplicada
    </Typography>
    <Typography variant='h5'>
      Participantes
    </Typography>
    <Typography sx={{px:2}} variant='h6'>
      Samuel Hernandez
    </Typography>
    <Typography sx={{px:2}} variant='h6'>
      Ana Londoño
    </Typography>
    </>
  )
}

export default IndexPage

export const Head = () => <title>Index</title>
