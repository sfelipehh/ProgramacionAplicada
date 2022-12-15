package com.sa.programacionaplicada.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
public class EventoDeGasto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fecha;

    private String hora;
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "localidad_id")
    @JsonIgnoreProperties({"sede","cuadrillas"})
    private Localidad localidad;

    private String descripcion;
    private Long valor;
    private Boolean aprobado = Boolean.FALSE;

    @ManyToOne()
    @JoinColumn(name = "empleado_id")
    @JsonIgnoreProperties({"cuadrilla","eventosDeGasto","sede"})
    private Empleado empleado;

    @ManyToOne()
    @JoinColumn(name = "cuadrilla_id")
    @JsonIgnoreProperties({"localidades","sede","empleados","eventosDeGasto","supervisorCuadrilla"})
    private Cuadrilla cuadrilla;

    public Cuadrilla getCuadrilla() {
        return cuadrilla;
    }

    public void setCuadrilla(Cuadrilla cuadrilla) {
        this.cuadrilla = cuadrilla;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventoDeGasto that = (EventoDeGasto) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "EventoDeGasto{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", localidad=" + localidad +
                ", descripcion='" + descripcion + '\'' +
                ", valor=" + valor +
                ", aprobado=" + aprobado +
                ", empleado=" + empleado +
                ", cuadrilla=" + cuadrilla +
                '}';
    }
}
