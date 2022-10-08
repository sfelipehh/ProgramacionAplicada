package com.sa.programacionaplicada.logica;

public class PiramideBaseCuadrada {
    private double lado;

    private double altura;

    private double volumen;


    public void setLado(double lado) {
        this.lado = lado;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getVolumen() {
        return volumen;
    }

    public double calcularvolumen(double lado, double altura){
        volumen= (Math.PI* (lado*lado)* altura)/3;
        return volumen;
    }
}
