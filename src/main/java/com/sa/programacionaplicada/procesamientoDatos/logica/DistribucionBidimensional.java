package com.sa.programacionaplicada.procesamientoDatos.logica;/*Author:sfeli*/

import java.util.Arrays;

public class DistribucionBidimensional {

    public int[] totales(int[][] equipos){
        int[] totales = new int[equipos.length];
        for (int i = 0; i < equipos.length; i++) {
            int total_piso = 0;
            for (int j = 0; j < equipos[0].length; j++) {
                total_piso += equipos[i][j];
            }
            totales[i] = total_piso;
        }
        return totales;
    }
    public double[] porcentajes(int[][] equipos){
        double[] porcentajes = new double[equipos.length];
        int suma_total = 0;
        int[] totales = totales(equipos);
        for (int i = 0; i < totales.length; i++) {
            suma_total+=totales[i];
        }
        for (int i = 0; i < porcentajes.length; i++) {
            porcentajes[i] = ( ((double)totales[i]) / suma_total);
        }
        return porcentajes;
    }
    public int[][] buscar(int cantidad_equipos, int[][] equipos){
        int[][] encontrados = new int[equipos.length*equipos[0].length][2];
        int aux = 0;
        for (int i = 0; i < equipos.length; i++) {
            for (int j = 0; j < equipos[0].length; j++) {
                if(equipos[i][j]==cantidad_equipos){
                    encontrados[aux] = new int[]{i,j};
                }else {
                    encontrados[aux] = new int[]{-1,-1};
                }
                aux++;
            }
        }
        System.out.println(Arrays.toString(encontrados));
        return encontrados;
    }
    public int[] presupuesto(int presupuesto,int[][] equipos){
        int[] presupuestos = new int[equipos.length];
        double[] porcentajes = porcentajes(equipos);
        for (int i = 0; i < porcentajes.length; i++) {
            presupuestos[i] = (int) (presupuesto*porcentajes[i]);
        }
        return presupuestos;
    }
}
