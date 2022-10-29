/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sa.programacionaplicada.logica;

/**
 *
 * @author Estudiantes
 */
public class Operaciones {
    
    
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
    
}
