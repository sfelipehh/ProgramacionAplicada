package com.sa.programacionaplicada.logica;

public class Cono {

    private double radio;

    private double altura;

    private double volumen;


    public void setRadio(double radio) {
        this.radio = radio;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getVolumen() {
        return volumen;
    }

    public double calcularvolumen(double radio, double altura){
        volumen= (Math.PI* (radio*radio)* altura)/3;
        return volumen;
    }
}
