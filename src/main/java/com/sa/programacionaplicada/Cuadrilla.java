package com.sa.programacionaplicada;

public class Cuadrilla {

    private long id;
    private String nombre;
    private long idsede;
    private long cantidadempleados;
    private long cupoasignado;
    private long cuporestante;

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

    public long getIdsede() {
        return idsede;
    }

    public void setIdsede(long idsede) {
        this.idsede = idsede;
    }

    public long getCantidadempleados() {
        return cantidadempleados;
    }

    public void setCantidadempleados(long cantidadempleados) {
        this.cantidadempleados = cantidadempleados;
    }

    public long getCupoasignado() {
        return cupoasignado;
    }

    public void setCupoasignado(long cupoasignado) {
        this.cupoasignado = cupoasignado;
    }

    public long getCuporestante() {
        return cuporestante;
    }

    public void setCuporestante(long cuporestante) {
        this.cuporestante = cuporestante;
    }
}
