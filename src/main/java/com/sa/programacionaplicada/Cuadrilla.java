package com.sa.programacionaplicada;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="cuadrilla")

public class Cuadrilla {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id",nullable = false )

    private Long id;
    private String nombre;
    private Long cantidadempleados;
    private Long cupoasignado;
    private Long cuporestante;

    @OneToMany(mappedBy = "cuadrilla", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"cuadrilla"})
    private Set<Empleado> empleados = new LinkedHashSet<>();


    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCantidadempleados() {
        return cantidadempleados;
    }

    public void setCantidadempleados(long cantidadempleados) {
        this.cantidadempleados = cantidadempleados;
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
}
