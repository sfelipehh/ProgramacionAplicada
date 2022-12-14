package com.sa.programacionaplicada.data.entities;/*Author:sfeli*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "supervisor_cuadrilla")
public class SupervisorCuadrilla {
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "cuadrilla_id")
    private Cuadrilla cuadrilla;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "empleado_id")
    @JsonIgnoreProperties({"cuadrilla", "eventosDeGasto","sede"})
    private Empleado empleado;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

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

    @Override
    public String toString() {
        return "SupervisorCuadrilla{" +
                "cuadrilla=" + cuadrilla +
                ", empleado=" + empleado +
                ", id=" + id +
                '}';
    }
}
