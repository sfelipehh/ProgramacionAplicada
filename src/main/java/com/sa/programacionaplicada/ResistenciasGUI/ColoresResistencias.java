package com.sa.programacionaplicada.ResistenciasGUI;/*Author:sfeli*/

import javafx.scene.paint.*;

import java.util.List;

public enum ColoresResistencias {
    NEGRO(Color.BLACK),
    CAFE(Color.BROWN),
    ROJO(Color.RED),
    NARANJA(Color.ORANGE),
    AMARILLO(Color.YELLOW),
    VERDE(Color.GREEN),
    AZUL(Color.BLUE),
    VIOLETA(Color.web("#dc2edc")),
    GRIS(Color.GRAY),
    BLANCO(Color.WHITE),
    DORADO(new LinearGradient(0.5,0.5,0.8,1.0,true, CycleMethod.NO_CYCLE,new Stop(0,Color.GOLD), new Stop(1,Color.WHITESMOKE))),
    PLATA(new LinearGradient(0.5,0.5,0.8,1.0,true, CycleMethod.NO_CYCLE,new Stop(0,Color.SILVER), new Stop(1,Color.WHITESMOKE)));

    private final Paint paint;
    ColoresResistencias(Paint paint){
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }
    public static List<ColoresResistencias> getDigits(){
        return List.of(values()).subList(0,10);
    }
    public static List<ColoresResistencias> getBasicTolerances(){
        return List.of(DORADO,PLATA);
    }
    public static List<ColoresResistencias> getExtraTolerances(){
        return List.of(CAFE,ROJO,VERDE,AZUL,VIOLETA,GRIS);
    }
}
