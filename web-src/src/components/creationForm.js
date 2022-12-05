import * as React from 'react'
import { Box, Button,TextField } from '@mui/material'
import { useFormik } from 'formik'

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
        <TextField fullWidth margin='dense' variant='outlined' key={(field.id)+index}
          id={field.id}
          name={field.id}
          type={field.type}
          label={field.name}
          InputLabelProps={ (field.type === 'date' || field.type === 'time') ? { shrink: true} : ''}
          required={field.required}
          value={formik.values[field.id]}
          onChange={formik.handleChange}
          error={formik.touched[field.id] && Boolean(formik.errors[field.id])}
          helperText={formik.touched[field.id] && formik.errors[field.id]}
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