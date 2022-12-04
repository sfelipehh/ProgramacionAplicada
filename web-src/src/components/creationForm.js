import * as React from 'react'
import { Box, Button,TextField } from '@mui/material'
import { useFormik } from 'formik'

const capitalize = ([first, ...rest]) =>
  first.toUpperCase() + (rest.join('').toLowerCase());

const CreationForm = ({formName, fields, initialValues , validationSchema, onSubmit, width, height }) => {
  const formik = useFormik({
    initialValues: initialValues,
      onSubmit: onSubmit,
      validationSchema : validationSchema
    })
  
  return (
  <Box sx={{
      width : width || 'auto',
      height: height || 'auto',
      padding: 2
      }}>
    <form onSubmit={formik.handleSubmit} style={{width:'100%', height:'100%' }}>
      {fields.map((field,index) => 
        <TextField fullWidth margin='dense' variant='outlined' key={field+index}
          id={field}
          name={field}
          label={capitalize(field)}
          value={formik.values[field]}
          onChange={formik.handleChange}
          error={formik.touched[field] && Boolean(formik.errors[field])}
          helperText={formik.touched[field] && formik.errors[field]}
        />
      )}
      <Button type='submit' color='primary' variant='contained' fullWidth>
        {formName}
      </Button>
    </form>
  </Box>
  )
}

export default CreationForm