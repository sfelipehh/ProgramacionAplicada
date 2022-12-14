package com.sa.programacionaplicada.data.entities;/*Author:sfeli*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
@Table(name = "administrador_sede")
public class AdministradorSede {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "empleado_id")
    @JsonIgnoreProperties({"cuadrilla","sede","eventosDeGasto","DNI","celular","email","cupoAsignado","cupoRestante"})
    private Empleado empleado;

    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "sede_id")
    @Cascade({CascadeType.SAVE_UPDATE})
    @JsonIgnoreProperties({"localidades","cuadrillas","empleados","administradorSede","direccion","nombre"})
    private Sede sede;

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
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
        return "AdministradorSede{" +
                "id=" + id +
                ", empleado=" + (empleado!=null ? empleado.getId() : null) +
                ", sede=" + (sede != null ? sede.getId() : null) +
                '}';
    }
}
