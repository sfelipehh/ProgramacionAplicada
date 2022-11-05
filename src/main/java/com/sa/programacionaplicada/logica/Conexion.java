package com.sa.programacionaplicada.logica;
import java.sql.*;

public class Conexion {
    public Connection getConexion(){
        String connectString = "jdbc:mysql://localhost:3306/vehiculosyconductores";
        String user = "saProgramacion";
        String password = "saProgramacion";
        try
        {
            return DriverManager.getConnection(connectString, user , password);
        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
