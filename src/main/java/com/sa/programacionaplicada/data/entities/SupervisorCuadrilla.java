package com.sa.programacionaplicada.data.entities;/*Author:sfeli*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "supervisor_cuadrilla")
public class SupervisorCuadrilla {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "cuadrilla_id")
    @JsonIgnoreProperties({"supervisorCuadrilla", "localidades", "sede", "empleados", "eventosDeGasto","cupoAsignado","cupoRestante"})
    private Cuadrilla cuadrilla;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "empleado_id")
    @JsonIgnoreProperties({"cuadrilla","sede","eventosDeGasto","DNI","celular","email","cupoAsignado","cupoRestante"})
    private Empleado empleado;


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
