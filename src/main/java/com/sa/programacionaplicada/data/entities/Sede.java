package com.sa.programacionaplicada.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;
    private String nombre;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sede", orphanRemoval = true)
    @JsonIgnoreProperties({"cuadrillas","sede"})
    private Set<Localidad> localidades = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sede", orphanRemoval = true)
    @JsonIgnoreProperties({"sede","localidades","empleados","eventosDeGasto"})
    private Set<Cuadrilla> cuadrillas = new LinkedHashSet<>();

    private String direccion;
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "sede", orphanRemoval = true, fetch = FetchType.EAGER)
    private AdministradorSede administradorSede;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sede", orphanRemoval = true)
    @JsonIgnoreProperties({"sede","cuadrilla","eventosDeGasto"})
    private Set<Empleado> empleados = new LinkedHashSet<>();


    public AdministradorSede getAdministradorSede() {
        return administradorSede;
    }

    public void setAdministradorSede(AdministradorSede administradorSede) {
        this.administradorSede = administradorSede;
    }

    public Set<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(Set<Localidad> localidades) {
        this.localidades = localidades;
    }

    public Set<Cuadrilla> getCuadrillas() {
        return cuadrillas;
    }

    public void setCuadrillas(Set<Cuadrilla> cuadrillas) {
        this.cuadrillas = cuadrillas;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localidades=" + Arrays.toString(localidades.stream().map(Localidad::getId).toArray()) +
                ", cuadrillas=" + Arrays.toString(cuadrillas.stream().map(Cuadrilla::getId).toArray()) +
                ", direccion='" + direccion + '\'' +
                ", administradorSede=" + administradorSede +
                ", empleados=" + Arrays.toString(empleados.stream().map(Empleado::getId).toArray()) +
                '}';
    }
}
