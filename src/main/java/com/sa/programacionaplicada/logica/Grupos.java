package com.sa.programacionaplicada.logica;/*Author:sfeli*/

import javafx.beans.property.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Grupos {
    private static Connection con;
    private static Conexion cx;

    private LongProperty id = new SimpleLongProperty(this, "id",0);
    private IntegerProperty asignatura = new SimpleIntegerProperty(this, "asignatura");
    private StringProperty salonAsignado = new SimpleStringProperty(this,"salonAsignado");
    private StringProperty docenteAsignado = new SimpleStringProperty(this,"docenteAsignado");
    private IntegerProperty cantidadInscritos = new SimpleIntegerProperty(this, "cantidadInscritos",0);

    public Grupos(){}
    public Grupos(Long id){
        setId(id);
    }
    public static ArrayList<Grupos> consultarTodos(){
        ArrayList<Grupos> resultList = new ArrayList<>();
        try{
            cx = new Conexion();
            con = cx.getConexion();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM grupos");
            while (rs.next()){
                Grupos actual_row = new Grupos();
                actual_row.setId(Long.parseLong(rs.getString("Codigo_de_grupo")));
                actual_row.setAsignatura(rs.getInt("Id_Asignatura"));
                actual_row.setSalonAsignado(rs.getString("Salon_Asignado"));
                actual_row.setDocenteAsignado(rs.getString("Docente_Asignado"));
                actual_row.setCantidadInscritos(rs.getInt("Numero_de_Estudiantes_Inscritos"));
                actual_row.setHorario(rs.getString("Horario"));
                resultList.add(actual_row);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return resultList;
    }
    public boolean actualizarGrupo(){
        try{
            cx = new Conexion();
            con = cx.getConexion();
            Statement statement = con.createStatement();
            statement.executeUpdate("UPDATE grupos SET `Id_Asignatura`");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean consultarPorCodigo(Long id){
        try {
            cx = new Conexion();
            con = cx.getConexion();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `grupos` WHERE `Codigo_de_grupo` = " + id);
            while(rs.next()){
                this.setId(id);
                this.setAsignatura(rs.getInt("Id_Asignatura"));
                this.setSalonAsignado(rs.getString("Salon_Asignado"));
                this.setDocenteAsignado(rs.getString("Docente_Asignado"));
                this.setCantidadInscritos(rs.getInt("Numero_de_Estudiantes_Inscritos"));
                this.setHorario(rs.getString("Horario"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public Integer getAsignatura() {
        return asignatura.get();
    }

    public IntegerProperty asignaturaProperty() {
        return asignatura;
    }

    public void setAsignatura(Integer asignatura) {
        this.asignatura.set(asignatura);
    }

    public String getSalonAsignado() {
        return salonAsignado.get();
    }

    public StringProperty salonAsignadoProperty() {
        return salonAsignado;
    }

    public void setSalonAsignado(String salonAsignado) {
        this.salonAsignado.set(salonAsignado);
    }

    public String getDocenteAsignado() {
        return docenteAsignado.get();
    }

    public StringProperty docenteAsignadoProperty() {
        return docenteAsignado;
    }

    public void setDocenteAsignado(String docenteAsignado) {
        this.docenteAsignado.set(docenteAsignado);
    }

    public int getCantidadInscritos() {
        return cantidadInscritos.get();
    }

    public IntegerProperty cantidadInscritosProperty() {
        return cantidadInscritos;
    }

    public void setCantidadInscritos(int cantidadInscritos) {
        this.cantidadInscritos.set(cantidadInscritos);
    }

    public String getHorario() {
        return horario.get();
    }

    public StringProperty horarioProperty() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario.set(horario);
    }

    private StringProperty horario = new SimpleStringProperty(this,"horario");
}
