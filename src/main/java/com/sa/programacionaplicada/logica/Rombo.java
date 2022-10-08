package com.sa.programacionaplicada.logica;

public class Rombo {

    private double diagonalmayor;

    private double diagonalmenor;

    private double area;

    public void setDiagonalmenor(double diagonalmenor) {
        this.diagonalmenor = diagonalmenor;
    }

    public void setDiagonalmayor(double diagonalmayor) {
        this.diagonalmayor = diagonalmayor;
    }

    public double getArea() {
        return area;
    }

    public double calculararea(double diagonalmayor, double diagonalmenor){
        area= (diagonalmayor*diagonalmenor)/2;
        return area;
    }
}
