package com.sa.programacionaplicada.logica;

public class OperacionesBasicas {

    public static double sumar(double a, double b){
        double suma;
        suma= a + b;
        return suma;
    }

    public static double restar(double a, double b){
        double resta;
        resta= a-b;
        return resta;
    }

    public static double multiplicar(double a, double b){
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


}