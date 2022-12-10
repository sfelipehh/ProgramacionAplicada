package com.sa.programacionaplicada;

public class Empleado {

    private long id;
    private long dni;
    private String nombres;
    private String apellidos;
    private String celular;
    private String email;
    private String fechadenacimiento;
    private long idcuadrilla;
    private long cupoasignado;
    private long cuporestante;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(String fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public long getIdcuadrilla() {
        return idcuadrilla;
    }

    public void setIdcuadrilla(long idcuadrilla) {
        this.idcuadrilla = idcuadrilla;
    }

    public long getCupoasignado() {
        return cupoasignado;
    }

    public void setCupoasignado(long cupoasignado) {
        this.cupoasignado = cupoasignado;
    }

    public long getCuporestante() {
        return cuporestante;
    }

    public void setCuporestante(long cuporestante) {
        this.cuporestante = cuporestante;
    }
}
