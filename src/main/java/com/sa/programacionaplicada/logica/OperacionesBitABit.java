package com.sa.programacionaplicada.logica;/*Author:sfeli*/

public class OperacionesBitABit extends OperacionesBasicas{
    public double AND(double a, double b){
        return (int)a & (int)b;
    }
    public double OR(double a, double b){
        return (int)a | (int)b;
    }
    public double XOR(double a, double b){
        return (int)a ^ (int)b;
    }
    public double NOT(double a){
        return ~((int)a);
    }
    public double NAND(double a, double b){
        return NOT(AND(a,b));
    }
    public double NOR(double a, double b){
        return NOT(OR(a, b));
    }
}
