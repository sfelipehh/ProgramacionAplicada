package com.sa.programacionaplicada.logica;

public class OperacionesTrigonometricas extends OperacionesBasicas{



    public static double calcularseno(double numero){
        double resultado;
        double angulo= numero;
        double radianes= Math.toRadians(angulo);
        resultado= Math.sin(radianes);
        return resultado;
    }
    public static double calcularcoseno(double numero){
        double resultado;
        double angulo= numero;
        double radianes= Math.toRadians(angulo);
        resultado= Math.cos(radianes);
        return resultado;
    }
    public static double calculartangente(double numero){
        double resultado;
        double angulo= numero;
        double radianes= Math.toRadians(angulo);
        resultado= Math.tan(radianes);
        return resultado;
    }
}
