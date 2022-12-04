import * as React from 'react'
import { Button,TextField } from '@mui/material'
import { useFormik } from 'formik'
import { Head } from '../pages/userCreate';

const capitalize = ([first, ...rest]) =>
  first.toUpperCase() + (rest.join('').toLowerCase());

const CreationForm = ({formName, fields, initialValues , validationSchema, onSubmit }) => {
  const formik = useFormik({
    initialValues: initialValues,
      onSubmit: onSubmit,
      validationSchema : validationSchema
    })
  
  return (
  <form onSubmit={formik.handleSubmit}>
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
  </form>)
}

export default CreationForm