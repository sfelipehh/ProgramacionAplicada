package com.sa.programacionaplicada;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name="empleado")

public class Empleado {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id",nullable = false )

    private Long id;
    private Long dni;
    private String nombres;
    private String apellidos;
    private String celular;
    private String email;
    private String fechadenacimiento;

    private Long cupoasignado;
    private Long cuporestante;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuadrilla_id")
    @JsonIgnoreProperties({"empleados"})
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

    public Long getDni() {
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



    public Long getCupoasignado() {
        return cupoasignado;
    }

    public void setCupoasignado(long cupoasignado) {
        this.cupoasignado = cupoasignado;
    }

    public Long getCuporestante() {
        return cuporestante;
    }

    public void setCuporestante(long cuporestante) {
        this.cuporestante = cuporestante;
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
}
