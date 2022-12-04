import * as React from 'react'
import { Box, Button,TextField } from '@mui/material'
import { useFormik } from 'formik'

const capitalize = ([first, ...rest]) =>
  first.toUpperCase() + (rest.join('').toLowerCase());


const getFieldType = (validationSchema , filedKey)=>{
  const type = validationSchema.describe().fields[filedKey].type
  if(type === 'string'){
    return 'text'
  }else {
    return type
  }

}

const getFieldRequired = (validationSchema, fieldKey)=>{
  const required = validationSchema.describe().fields[fieldKey].tests.some((element) => element.name='required')
  return required
}

const CreationForm = ({formName, fields, initialValues , validationSchema, onSubmit, width, height }) => {
  const formik = useFormik({
    initialValues: initialValues,
      onSubmit: onSubmit,
      validationSchema : validationSchema
    })
    getFieldRequired(validationSchema,'cargo')
  return (
  <Box sx={{
      width : width || 'auto',
      height: height || 'auto',
      padding: 2
      }}>
    <form onSubmit={formik.handleSubmit} style={{width:'100%', height:'100%' }}>
      {fields.map((field,index) => 
        <TextField fullWidth margin='dense' variant='outlined' key={(field)+index}
          id={field}
          name={field}
          type={getFieldType(validationSchema,field)}
          label={
            getFieldType(validationSchema,field) === 'date' ? '' : capitalize(field)
          }
          required={getFieldRequired(validationSchema,field)}
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