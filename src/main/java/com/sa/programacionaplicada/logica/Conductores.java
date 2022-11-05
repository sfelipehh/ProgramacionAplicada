package com.sa.programacionaplicada.logica;

import java.sql.*;

public class Conductores {

   static Connection con;
   static Conexion cx;

   private long identificacion;
   private String nombre;
   private String apellido;
   private String genero;
   private String celular;
   private String fijo;
   private String fecha;
   private String correo;
   private String numerolicencia;
   private String categorialicencia;
   private String turno;
   private String ciudad;
   private String direccion;
   private String barrio;
   private String estadocivil;
   private String codigovehiculo;

   public long getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(long identificacion) {
      this.identificacion = identificacion;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getApellido() {
      return apellido;
   }

   public void setApellido(String apellido) {
      this.apellido = apellido;
   }

   public String getGenero() {
      return genero;
   }

   public void setGenero(String genero) {
      this.genero = genero;
   }

   public String getCelular() {
      return celular;
   }

   public void setCelular(String celular) {
      this.celular = celular;
   }

   public String getFijo() {
      return fijo;
   }

   public void setFijo(String fijo) {
      this.fijo = fijo;
   }

   public String getFecha() {
      return fecha;
   }

   public void setFecha(String fecha) {
      this.fecha = fecha;
   }

   public String getCorreo() {
      return correo;
   }

   public void setCorreo(String correo) {
      this.correo = correo;
   }

   public String getNumerolicencia() {
      return numerolicencia;
   }

   public void setNumerolicencia(String numerolicencia) {
      this.numerolicencia = numerolicencia;
   }

   public String getCategorialicencia() {
      return categorialicencia;
   }

   public void setCategorialicencia(String categorialicencia) {
      this.categorialicencia = categorialicencia;
   }

   public String getTurno() {
      return turno;
   }

   public void setTurno(String turno) {
      this.turno = turno;
   }

   public String getCiudad() {
      return ciudad;
   }

   public void setCiudad(String ciudad) {
      this.ciudad = ciudad;
   }

   public String getDireccion() {
      return direccion;
   }

   public void setDireccion(String direccion) {
      this.direccion = direccion;
   }

   public String getBarrio() {
      return barrio;
   }

   public void setBarrio(String barrio) {
      this.barrio = barrio;
   }

   public String getEstadocivil() {
      return estadocivil;
   }

   public void setEstadocivil(String estadocivil) {
      this.estadocivil = estadocivil;
   }

   public String getCodigovehiculo() {
      return codigovehiculo;
   }

   public void setCodigovehiculo(String codigovehiculo) {
      this.codigovehiculo = codigovehiculo;
   }

   public boolean GuardarConductor(){
      try
      {
         cx= new Conexion();
         con= cx.getConexion();
         PreparedStatement stmt = con.prepareStatement("INSERT INTO Conductores (identificacion, Nombre, Apellido, Genero, Celular, Fijo, Fecha de Nacimiento, Correo Electronico, Numero de Licencia, Categoria de licencia, Turno, Ciudad,  Direccion, Barrio, Estado Civil, Codigo Vehiculo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

         stmt.setString(1, "" + this.identificacion);
         stmt.setString(2,this.nombre);
         stmt.setString(3,this.apellido);
         stmt.setString(4,this.genero);
         stmt.setString(5,this.celular);
         stmt.setString(6,this.fijo);
         stmt.setString(7,this.fecha);
         stmt.setString(8,this.correo);
         stmt.setString(9,this.numerolicencia);
         stmt.setString(10,this.categorialicencia);
         stmt.setString(11,this.turno);
         stmt.setString(13,this.ciudad);
         stmt.setString(14,this.direccion);
         stmt.setString(12,this.barrio);
         stmt.setString(13,this.estadocivil);
         stmt.setString(14,this.codigovehiculo);

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

   public boolean ActualizarConductor(){
      try {
         cx = new Conexion();
         con = cx.getConexion();
         PreparedStatement stmt = con.prepareStatement("UPDATE Clientes SET Nombre = '" +
                 this.nombre + "', Apellido = '" + this.apellido + "',Fijo='" + this.fijo + "',Celular='" + this.celular +
                 "' WHERE (identificacion = " + this.identificacion + ")");
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

   public boolean ConsultarConductor(long IdentificacionABuscar){
      try {
         boolean consultaOK = false;
         cx = new Conexion();
         con = cx.getConexion();
         Statement stmt = con.createStatement();

         ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_conductores WHERE id_conductor = " +
                 IdentificacionABuscar);

         if (rs.next()) {
            this.identificacion = Long.parseLong(rs.getString("id_conductor"));
            this.nombre = rs.getString("name_Conductor");
            this.apellido = rs.getString("last_name_Conductor");
            this.genero = "K";
            this.celular = rs.getString("cellphone_Conductor");
            this.fijo = rs.getString("phone_Conductor");
            this.fecha = rs.getString("birth_date_Conductor");
            this.correo = rs.getString("email_Conductor");
            this.numerolicencia = rs.getString("id_Licencia_Conductor");
            this.categorialicencia = rs.getString("licence_category_Conductor");
            this.turno = rs.getString("turn_Conductor");
            this.ciudad = rs.getString("residence_city_Conductor");
            this.direccion = rs.getString("address_Conductor");
            this.estadocivil = rs.getString("civil_state_Conductor");
            this.codigovehiculo = "12";

            consultaOK = true;
         }

         stmt.close();
         con.close();

         return consultaOK;
      }
      catch ( Exception e )
         {
            e.printStackTrace();
            return false;
         }
      }
   public boolean EliminarConductor(long IdentificacionABuscar){
      try {
         cx = new Conexion();
         con = cx.getConexion();
         PreparedStatement stmt = con.prepareStatement("DELETE FROM Conductores WHERE (identificacion = " + IdentificacionABuscar + ")");
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










