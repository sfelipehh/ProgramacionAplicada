package com.sa.programacionaplicada.logica;

public class PiramideBaseCuadrada extends Figura{
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

    public double calcularvolumen(){
        volumen= (Math.PI* (lado*lado)* altura)/3;
        return volumen;
    }
}
