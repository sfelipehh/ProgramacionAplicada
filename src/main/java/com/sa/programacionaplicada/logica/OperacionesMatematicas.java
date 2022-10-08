package com.sa.programacionaplicada.logica;

public class OperacionesMatematicas extends OperacionesTrigonometricas{

    public static double calcularpotencia(double numero, double potencia) {
        double resultado = 1;
        for (int i = 1; i <= potencia; i++) {
            resultado = resultado * numero;
        }
        return resultado;
    }


    public static double calcularraiz(double numero) {

        double raiz;
        raiz = Math.sqrt(numero);
        return raiz;
    }


    public static double logaritmonatural(double a) {
        double resultado = Math.log(a);
        return resultado;
    }

    public static double modulo(double a) {
        double resultado = Math.abs(a);
        return resultado;
    }

    public static double logaritmo(double a) {
        double resultado = Math.log10(a);
        return resultado;
    }


    public static double Inverso( double a){
        double resultado = 1/a;
        return resultado;
    }
}


