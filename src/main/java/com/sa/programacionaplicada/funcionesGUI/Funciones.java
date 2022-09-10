package com.sa.programacionaplicada.funcionesGUI;/*Author:sfeli*/

import java.lang.Math;


public class Funciones {



    public static double  calcularpotencia(double numero, double potencia){
        double resultado=1;
        for (int i=1; i<=potencia; i++){
            resultado= resultado * numero;
        }
        return resultado;
    }

    public static double calcularseno(double numero){
        double resultado;
        double angulo= numero;
        double radianes= Math.toRadians(numero);
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

    public static double calcularraiz(double numero){
        double raiz;
        raiz= Math.sqrt(numero);
        return raiz;
    }

    public static int fibonacci(int n)  {
        if(n == 0)
            return 0;
        else if(n == 1)
            return 1;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static double factorial (int numero){

        double resultado=1;
        for(int i= 2; i<= numero; i++){
            resultado *= i;
        }
        return resultado;

    }

}
