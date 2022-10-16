package com.sa.programacionaplicada.logica;

public class Cilindro extends Figura{

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

   public double calcularvolumen(){
        volumen= Math.PI* (radio*radio)* altura;
        return volumen;
   }
}