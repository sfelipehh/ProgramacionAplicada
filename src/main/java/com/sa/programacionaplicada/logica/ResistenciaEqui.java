/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sa.programacionaplicada.logica;

/**
 *
 * @author Estudiantes
 */
 import java.util.ArrayList;

public class ResistenciaEqui extends Operaciones{

    private ArrayList<Double> valoresResistencia;
    public void setValoresResistencia(ArrayList<Double> valoresResistencia){
        this.valoresResistencia = valoresResistencia;
    }
    public ArrayList<Double> getValoresResistencia(){
        return this.valoresResistencia;
    }
    public double calcularRenserie(){
        
        double Req=0;

        for (int i=0; i<valoresResistencia.size();i++){
        Req = sumar(valoresResistencia.get(i), Req);
        }
        return Req;
    }
    
    public double calcularRparalelo(){
        
       double Req=0;
       
       for (int i=0; i<valoresResistencia.size();i++){
        Req = sumar(dividir(1,valoresResistencia.get(i)), Req);
    }
       return dividir(1,Req);
   }     

    
   
}
