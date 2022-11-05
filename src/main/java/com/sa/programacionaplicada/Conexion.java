package com.sa.programacionaplicada;
import java.sql.*;

public class Conexion {
    public Connection getConexion(){
        String driver = "com.mysql.jdbc.Driver";
        String connectString = "jdbc:mysql://";
        String user = "usuario MySql";
        String password = "password MySQL";
        try
        {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user , password);
            return con;
        }
        catch ( Exception e )
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
}
