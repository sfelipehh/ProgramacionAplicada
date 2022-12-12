package com.sa.programacionaplicada;

import jakarta.persistence.*;

@Entity
@Table(name="eventodegasto")

public class EventodeGasto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id",nullable = false )

    private Long id;
    private String fecha;
    private String hora;
    private Long idlocalidad;
    private Long idcuadrilla;
    private String descripcion;
    private Long valor;
    private Long idempleado;
    boolean aprobado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuadrilla_id")
    private Cuadrilla cuadrilla;

    public Cuadrilla getCuadrilla() {
        return cuadrilla;
    }

    public void setCuadrilla(Cuadrilla cuadrilla) {
        this.cuadrilla = cuadrilla;
    }

    public Long getId() {
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

    public Long getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(long idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public Long getIdcuadrilla() {
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

    public Long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public Long getIdempleado() {
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
