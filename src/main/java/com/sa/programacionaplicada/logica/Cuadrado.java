package com.sa.programacionaplicada.logica;

public class Cuadrado extends Figura{

    private double lado;

    private double area;

    public void setLado(double lado) {
        this.lado = lado;
    }

    public double getArea() {
        return area;
    }

    public double calculararea(){
        area=lado*lado;
        return area;
    }
}
