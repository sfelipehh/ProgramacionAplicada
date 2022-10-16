package com.sa.programacionaplicada.logica;/*Author:sfeli*/

public class Esfera extends Figura{
    private double radio;
    private double volumen;



    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getVolumen() {
        return volumen;
    }


    public double calcularvolumen(){

        volumen= (4/3) * Math.PI* (radio*radio*radio);
        return volumen;
    }


}
