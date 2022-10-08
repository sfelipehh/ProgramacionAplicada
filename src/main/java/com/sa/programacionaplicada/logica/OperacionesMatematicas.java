package com.sa.programacionaplicada.logica;

public class OperacionesMatematicas extends OperacionesTrigonometricas{

    public double calcularpotencia(double numero, double potencia) {
        double resultado = 1;
        for (int i = 1; i <= potencia; i++) {
            resultado = resultado * numero;
        }
        return resultado;
    }


    public double calcularraiz(double numero, double raiz) {

        double resultado;
        resultado = Math.pow(numero, 1/raiz);
        return resultado;
    }


    public double logaritmonatural(double a) {
        double resultado = Math.log(a);
        return resultado;
    }

    public double valorabsoluto(double a) {
        double resultado = Math.abs(a);
        return resultado;
    }

    public double logaritmo(double a) {
        double resultado = Math.log10(a);
        return resultado;
    }


    public double inverso( double a){
        double resultado = 1/a;
        return resultado;
    }

    public double x10Pow(double a){
        return calcularpotencia(10,a);
    }

    public double factorial (double numero){
        if (numero >=101){
            return -1;
        }
        double resultado=1;
        for(int i= 2; i<= numero; i++){
            resultado *= i;
        }
        return resultado;

    }

}


