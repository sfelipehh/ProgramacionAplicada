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
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Vehiculos (id_Vehiculo, placa_Vehiculo,type_Vehiculo,brand_Vehiculo,model_Vehiculo,desc_Vehiculo, start_oper_date_Vehiculo ) VALUES (?,?,?,?,?,?,?)");

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
            PreparedStatement stmt = con.prepareStatement("UPDATE Vehiculos SET placa_Vehiculo = '" +
                    this.placa + "', type_Vehiculo = '" + this.tipo + "', brand_Vehiculo ='" + this.marca + "', model_Vehiculo'" + this.modelo+
                    "', desc_Vehiculo='" +this.descripcion+"', start_oper_date_Vehiculo ='"+this.fechainicio+"' WHERE (id_Vehiculo= " + this.codigo + ")");
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

            ResultSet rs = stmt.executeQuery("SELECT * FROM Vehiculos where id_Vehiculo = " + CodigoABuscar);

            if (rs.next()) {
                rs.first();
                this.codigo = Long.parseLong(rs.getString("id_Vehiculo"));
                this.placa = rs.getString("placa_Vehiculo");
                this.tipo = rs.getString("type_Vehiculo");
                this.marca = rs.getString("brand_Vehiculo");
                this.modelo = rs.getString("model_Vehiculo");
                this.descripcion = rs.getString("desc_Vehiculo");
                this.fechainicio = rs.getString("start_oper_date_Vehiculo");
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
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Vehiculos WHERE (id_Vehiculo = " + CodigoABuscar + ")");
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
