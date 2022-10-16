package com.sa.programacionaplicada.logica;

public class Triangulo extends Figura {

    private double base;

    private double altura;

    private double area;

    public void setBase(double base) {
        this.base = base;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getArea() {
        return area;
    }

    public double calculararea(){

        area= (base*altura)/2;
        return area;
    }
}
