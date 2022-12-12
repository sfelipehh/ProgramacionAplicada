package com.sa.programacionaplicada;

import jakarta.persistence.*;

@Entity
@Table(name="localidad")

public class Localidad {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id",nullable = false )

    private Long id;
    private String nombre;
    private String calleinicio;
    private String callefin;
    private String carrerainicio;
    private String carrerafin;
    private Long idsede;

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

    public String getCalleinicio() {
        return calleinicio;
    }

    public void setCalleinicio(String calleinicio) {
        this.calleinicio = calleinicio;
    }

    public String getCallefin() {
        return callefin;
    }

    public void setCallefin(String callefin) {
        this.callefin = callefin;
    }

    public String getCarrerainicio() {
        return carrerainicio;
    }

    public void setCarrerainicio(String carrerainicio) {
        this.carrerainicio = carrerainicio;
    }

    public String getCarrerafin() {
        return carrerafin;
    }

    public void setCarrerafin(String carrerafin) {
        this.carrerafin = carrerafin;
    }

    public Long getIdsede() {
        return idsede;
    }

    public void setIdsede(long idsede) {
        this.idsede = idsede;
    }
}
