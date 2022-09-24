package com.sa.programacionaplicada.procesamientoDatos.logica;/*Author:sfeli*/

public class DistribucionUnidimensional {

    public int promedio(int[] datos) {
        double suma_promedio = 0;
        for (int i = 0; i < datos.length; i++) {
            suma_promedio += datos[i];
        }
        double promedio = suma_promedio / datos.length;
        return (int) Math.ceil(promedio);
    }

    public int maximo(int[] equipos) {
        int aux = 0;
        for (int i = 0; i < equipos.length; i++) {
            if(equipos[i] > equipos[aux]){
                aux=i;
            }
        }
        return aux;
    }

    public int minimo(int[] equipos) {
        int aux = 0;
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] < equipos[aux]){
                aux = i;
            }
        }
        return aux;
    }

    public int[] distribucion(int operadores, int[] equipos){
        int operadores_restantes = operadores;
        int[] distribucion = new int[equipos.length];
        distribucion[minimo(equipos)] = 2;
        operadores_restantes-=2;
        //60% de los operadores restantes
        int operadores_06 = (int) Math.floor(operadores_restantes*0.6);
        distribucion[maximo(equipos)] = operadores_06;
        operadores_restantes-= operadores_06;
        int operadores_04 = (int) (Math.floor((operadores_restantes*0.4)) / (distribucion.length-2));
        for (int i = 0; i < distribucion.length; i++) {
            if (distribucion[i]==0){
                distribucion[i]=operadores_04;
            }
        }
        return distribucion;
    }
}
