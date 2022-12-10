package com.sa.programacionaplicada;

public class EventodeGasto {

    private long id;
    private String fecha;
    private String hora;
    private long idlocalidad;
    private long idcuadrilla;
    private String descripcion;
    private long valor;
    private long idempleado;
    boolean aprobado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public long getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(long idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public long getIdcuadrilla() {
        return idcuadrilla;
    }

    public void setIdcuadrilla(long idcuadrilla) {
        this.idcuadrilla = idcuadrilla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
}
