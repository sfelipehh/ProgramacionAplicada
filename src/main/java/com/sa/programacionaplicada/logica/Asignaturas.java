package com.sa.programacionaplicada.logica;/*Author:sfeli*/

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Asignaturas {

    private static final Set<Asignaturas> asignaturas_id_names = new LinkedHashSet<>();

    public static Set<Asignaturas> getAsignaturas_id_names() {
        return asignaturas_id_names;
    }

    protected Integer id;
    protected String name;

    public static void consultarTodos(){
        asignaturas_id_names.clear();
        try{
            Conexion cx = new Conexion();
            Connection con = cx.getConexion();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM asignaturas");
            while (rs.next()){
                Asignaturas actual_row = new Asignaturas();
                actual_row.id= rs.getInt("Id_Asignatura");
                actual_row.name = rs.getString("Nombre_Asignatura");
                asignaturas_id_names.add(actual_row);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static Asignaturas buscarPorId(Integer idS){
        for (Asignaturas asignatura:
             asignaturas_id_names) {
            if (Objects.equals(asignatura.id, idS)){
                return asignatura;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return name + "("+id+")";
    }
}
