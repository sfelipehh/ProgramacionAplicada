package com.sa.programacionaplicada.logica;

public class Circulo {

    private double radio;

    private double area;

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getArea() {
        return area;
    }

    public double calculararea(double radio){

        area= Math.PI* (radio*radio);
        return area;

    }
}
