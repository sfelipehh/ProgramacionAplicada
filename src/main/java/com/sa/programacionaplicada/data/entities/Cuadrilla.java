package com.sa.programacionaplicada.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Cuadrilla {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private Long cantidadEmpleados;
    private Long cupoAsignado;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "cuadrilla", orphanRemoval = true, fetch = FetchType.EAGER)
    private SupervisorCuadrilla supervisorCuadrilla;
    @OneToMany(mappedBy = "cuadrilla", orphanRemoval = true)
    @JsonIgnoreProperties({"sede","cuadrilla"})
    private Set<Localidad> localidades = new LinkedHashSet<>();
    private Long cupoRestante;
    @ManyToOne()
    @JoinColumn(name = "sede_id")
    @JsonIgnoreProperties({"localidades","cuadrillas","empleados","administradorSede"})
    private Sede sede;

    @OneToMany(mappedBy = "cuadrilla", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"cuadrilla","eventosDeGasto","sede"})
    private Set<Empleado> empleados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cuadrilla", orphanRemoval = true)
    @JsonIgnoreProperties({"cuadrilla"})
    private Set<EventoDeGasto> eventosDeGasto = new LinkedHashSet<>();

    public Set<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(Set<Localidad> localidades) {
        this.localidades = localidades;
    }

    public Set<EventoDeGasto> getEventosDeGasto() {
        return eventosDeGasto;
    }

    public void setEventosDeGasto(Set<EventoDeGasto> eventosDeGasto) {
        this.eventosDeGasto = eventosDeGasto;
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


    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Long getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(Long cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public Long getCupoAsignado() {
        return cupoAsignado;
    }

    public void setCupoAsignado(Long cupoAsignado) {
        this.cupoAsignado = cupoAsignado;
    }

    public Long getCupoRestante() {
        return cupoRestante;
    }

    public void setCupoRestante(Long cupoRestante) {
        this.cupoRestante = cupoRestante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cuadrilla cuadrilla = (Cuadrilla) o;
        return id != null && Objects.equals(id, cuadrilla.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public SupervisorCuadrilla getSupervisorCuadrilla() {
        return supervisorCuadrilla;
    }

    public void setSupervisorCuadrilla(SupervisorCuadrilla supervisorCuadrilla) {
        this.supervisorCuadrilla = supervisorCuadrilla;
    }

    @Override
    public String toString() {
        return "Cuadrilla{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidadEmpleados=" + cantidadEmpleados +
                ", supervisorCuadrilla=" + supervisorCuadrilla +
                ", cupoAsignado=" + cupoAsignado +
                ", localidades=" + Arrays.toString(localidades.stream().map(Localidad::getId).toArray()) +
                ", cupoRestante=" + cupoRestante +
                ", sede=" + (sede != null ? sede.getId() : null) +
                ", empleados=" + Arrays.toString(empleados.stream().map(Empleado::getId).toArray()) +
                ", eventosDeGasto=" + Arrays.toString(eventosDeGasto.stream().map(EventoDeGasto::getId).toArray()) +
                '}';
    }
}
