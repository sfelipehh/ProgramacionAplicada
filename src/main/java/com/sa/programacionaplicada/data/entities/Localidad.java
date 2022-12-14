package com.sa.programacionaplicada.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String calleInicio;
    private String calleFin;
    private String carreraInicio;
    private String carreraFin;
    @ManyToOne()
    @JoinColumn(name = "sede_id")
    @JsonIgnoreProperties({"localidades","cuadrillas","empleados"})
    private Sede sede;
    @ManyToOne
    @JoinColumn(name = "cuadrilla_id")
    @JsonIgnoreProperties({"localidades","sede","empleados","eventosDeGasto","supervisorCuadrilla"})
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
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalleInicio() {
        return calleInicio;
    }

    public void setCalleInicio(String calleInicio) {
        this.calleInicio = calleInicio;
    }

    public String getCalleFin() {
        return calleFin;
    }

    public void setCalleFin(String calleFin) {
        this.calleFin = calleFin;
    }

    public String getCarreraInicio() {
        return carreraInicio;
    }

    public void setCarreraInicio(String carreraInicio) {
        this.carreraInicio = carreraInicio;
    }

    public String getCarreraFin() {
        return carreraFin;
    }

    public void setCarreraFin(String carreraFin) {
        this.carreraFin = carreraFin;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Localidad localidad = (Localidad) o;
        return id != null && Objects.equals(id, localidad.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Localidad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", calleInicio='" + calleInicio + '\'' +
                ", calleFin='" + calleFin + '\'' +
                ", carreraInicio='" + carreraInicio + '\'' +
                ", carreraFin='" + carreraFin + '\'' +
                ", sede=" + (sede != null ? sede.getId() : null) +
                ", cuadrilla=" + (cuadrilla !=null ? cuadrilla.getId() : null) +
                '}';
    }
}
