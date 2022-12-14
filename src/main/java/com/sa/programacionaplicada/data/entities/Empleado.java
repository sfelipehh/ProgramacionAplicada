package com.sa.programacionaplicada.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "DNI")
    private Long DNI;
    private String nombres;
    private String apellidos;
    private String celular;
    private String email;
    private String fechaNacimiento;
    private Long cupoAsignado;
    private Long cupoRestante;

    @ManyToOne()
    @JoinColumn(name = "cuadrilla_id")
    @JsonIgnoreProperties({"sede","localidades","empleados","eventosDeGasto"})
    private Cuadrilla cuadrilla;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnoreProperties({"cuadrilla","empleado"})
    private Set<EventoDeGasto> eventosDeGasto = new LinkedHashSet<>();

    @ManyToOne()
    @JoinColumn(name = "sede_id")
    @JsonIgnoreProperties({"localidades","cuadrillas","empleados"})
    private Sede sede;

    public Set<EventoDeGasto> getEventosDeGasto() {
        return eventosDeGasto;
    }

    public void setEventosDeGasto(Set<EventoDeGasto> eventosDeGasto) {
        this.eventosDeGasto = eventosDeGasto;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public Long getDNI() {
        return DNI;
    }

    public void setDNI(Long DNI) {
        this.DNI = DNI;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Empleado empleado = (Empleado) o;
        return id != null && Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", DNI=" + DNI +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", cupoAsignado=" + cupoAsignado +
                ", cupoRestante=" + cupoRestante +
                ", cuadrilla=" + (cuadrilla !=null ? cuadrilla.getId() : null) +
                ", eventosDeGasto=" + Arrays.toString(eventosDeGasto.stream().map(EventoDeGasto::getId).toArray()) +
                ", sede=" + (sede != null ? sede.getId() : null) +
                '}';
    }
}
