/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sa.programacionaplicada.logica;

/**
 *
 * @author Estudiantes
 */
public class LeydeOhm extends Operaciones{
    
     
        private double voltaje;
        private double corriente;
        private double resistencia;
        private double potencia;
        
        public double calcularVoltaje(double corriente,double resistencia){
            setVoltaje(multiplicar(corriente,resistencia));
            return getVoltaje();
        }
        
        public double calcularCorriente(double voltaje, double resistencia){
            setCorriente(dividir(voltaje,resistencia));
            return getCorriente();
        }

        
        public double calcularPot3(double voltaje, double resistencia){
            setPotencia(dividir( multiplicar(voltaje,voltaje),resistencia));
            return getPotencia();
           
        }
        
        public double calcularPot2(double corriente, double resistencia){
            setPotencia(multiplicar(multiplicar(corriente,corriente),resistencia));
            return getPotencia();
        }
        
        public double calcularPot1(double voltaje, double corriente){
            setPotencia(multiplicar(voltaje,corriente));
            return getPotencia();
        }

    public double getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(double voltaje) {
        this.voltaje = voltaje;
    }

    public double getCorriente() {
        return corriente;
    }

    public void setCorriente(double corriente) {
        this.corriente = corriente;
    }

    public double getResistencia() {
        return resistencia;
    }

    public void setResistencia(double resistencia) {
        this.resistencia = resistencia;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }
}
