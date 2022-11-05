package com.sa.programacionaplicada.logica;
import com.sa.programacionaplicada.logica.Conexion;

import java.sql.*;

public class Vehiculos {
    static Connection con;
    static Conexion cx;

    private long codigo;
    private String placa;
    private String tipo;
    private String marca;
    private String modelo;
    private String descripcion;
    private String fechainicio;
    private String estadoasignacion;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getEstadoasignacion() {
        return estadoasignacion;
    }

    public void setEstadoasignacion(String estadoasignacion) {
        this.estadoasignacion = estadoasignacion;
    }

    public boolean GuardarVehiculo(){
        try
        {
            cx= new Conexion();
            con= cx.getConexion();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Vehiculos (codigo,placa,tipo,marca,modelo,descripcion, fechainicio, estadoasignacion) VALUES (?,?,?,?,?,?,?,?)");

            stmt.setString(1, "" + this.codigo);
            stmt.setString(2,this.placa);
            stmt.setString(3,this.marca);
            stmt.setString(4,this.modelo);
            stmt.setString(5,this.descripcion);
            stmt.setString(6,this.fechainicio);
            stmt.setString(7,this.estadoasignacion);

            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean ActualizarVehiculo(){
        try {
            cx = new Conexion();
            con = cx.getConexion();
            PreparedStatement stmt = con.prepareStatement("UPDATE Vehiculos SET Placa = '" +
                    this.placa + "', Tipo = '" + this.tipo + "', Marca ='" + this.marca + "', Modelo='" + this.modelo+
                    "' WHERE (Codigo de Vehiculo = " + this.codigo + ")");
            stmt.executeUpdate();
            stmt.close();
            con.close();

            return true;
        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean ConsultarVehiculo(long CodigoABuscar){
        try {
            boolean consultaOK = false;
            cx = new Conexion();
            con = cx.getConexion();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Vehiculos where codigo = " + CodigoABuscar);

            if (rs.next()) {
                rs.first();
                this.codigo = Long.parseLong(rs.getString("Codigo de Vehiculo"));
                this.placa = rs.getString("Placa");
                this.tipo = rs.getString("Tipo");
                this.marca = rs.getString("Marca");
                this.modelo = rs.getString("Modelo");
                this.descripcion = rs.getString("Descripcion");
                this.fechainicio = rs.getString("Fecha de Inicio");
                this.estadoasignacion = rs.getString("Estado de Asignacion");

                consultaOK = true;
            }
            stmt.close();
            con.close();
            return consultaOK;
        }
        catch ( Exception e )
            {
                System.out.println(e.getMessage());
                return false;
            }
        }

    public boolean EliminarVehiculo(long CodigoABuscar){

        try
        {
            cx = new Conexion();
            con = cx.getConexion();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Vehiculos WHERE (codigo = " + CodigoABuscar + ")");
            stmt.executeUpdate();
            stmt.close();
            con.close();
            return true;
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
