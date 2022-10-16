package com.sa.programacionaplicada.logica;/*Author:sfeli*/

public class Cubo extends Figura{

    private double lado;
    private double volumen;

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double getVolumen() {
        return volumen;
    }

    public void calcularvolumen() {
        volumen = lado*lado*lado;
    }
}
