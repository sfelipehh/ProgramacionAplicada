package com.sa.programacionaplicada;

public class Localidad {

    private long id;
    private String nombre;
    private String calleinicio;
    private String callefin;
    private String carrerainicio;
    private String carrerafin;
    private long idsede;

    public long getId() {
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

    public long getIdsede() {
        return idsede;
    }

    public void setIdsede(long idsede) {
        this.idsede = idsede;
    }
}
