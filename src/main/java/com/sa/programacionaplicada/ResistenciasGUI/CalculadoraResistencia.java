package com.sa.programacionaplicada.ResistenciasGUI;/*Author:sfeli*/

public class CalculadoraResistencia {

    public static double miliohm(double resistencia){
        double resultado;
        resultado= resistencia * 1000;
        return resultado;
    }

    public static double microohm(double resistencia){
        double resultado;
        resultado= resistencia* 1000000;
        return resultado;
    }

    public static double nanoohm(double resistencia){
        double resultado;
        resultado= resistencia* 100000000;
        return resultado;
    }

    public static double kiloohm(double resistencia){
        double resultado;
        resultado= resistencia* 0.001;
        return resultado;
    }

    public static double megaohm(double resistencia){
        double resultado;
        resultado= resistencia* 0.000001;
        return resultado;
    }

    public static double gigaohm(double resistencia){
        double resultado;
        resultado= resistencia*( 1/1000000000);
        return resultado;
    }
}
