package com.sa.programacionaplicada.logica;

public class OperacionesMatematicas {

    public static double  calcularpotencia(double numero, double potencia){
        double resultado=1;
        for (int i=1; i<=potencia; i++){
            resultado= resultado * numero;
        }
        return resultado;
    }


    public static double calcularraiz (double numero){

        double raiz;
        raiz= Math.sqrt(numero);
        return raiz;
    }


}


