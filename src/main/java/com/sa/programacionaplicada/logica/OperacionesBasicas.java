package com.sa.programacionaplicada.logica;

public class OperacionesBasicas {

    public double sumar(double a, double b){
        double suma;
        suma= a + b;
        return suma;
    }

    public double restar(double a, double b){
        double resta;
        resta= a-b;
        return resta;
    }

    public double multiplicar(double a, double b){
        double multiplicacion;
        multiplicacion= a*b;
        return multiplicacion;
    }


    public double dividir ( double a, double b){
        double respuesta=0;
        try{
            respuesta=a/b;
        }
        catch(Exception e){
            System.out.println("Error al dividir por 0");
        }
        return  respuesta;
    }

    public double modulo(double a, double b){
        return a%b;
    }
}