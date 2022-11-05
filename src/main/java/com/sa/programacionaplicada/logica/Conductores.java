package com.sa.programacionaplicada.logica;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.*;
import java.util.ArrayList;

public class Conductores {

   private static Connection con;
   private static Conexion cx;

   private final LongProperty id = new SimpleLongProperty(this,"id",0);
   private final LongProperty idLicencia = new SimpleLongProperty(this,"idLicencia");
   private final StringProperty name = new SimpleStringProperty(this,"name");
   private final StringProperty lastName = new SimpleStringProperty(this,"lastName");
   private final StringProperty phone = new SimpleStringProperty(this,"phone");
   private final StringProperty cellphone = new SimpleStringProperty(this,"cellphone");
   private final StringProperty birthDate = new SimpleStringProperty(this,"birthDate");
   private final StringProperty email = new SimpleStringProperty(this,"email");
   private final StringProperty licenseCategory = new SimpleStringProperty(this,"licenseCategory");
   private final StringProperty turn = new SimpleStringProperty(this,"turn");
   private final StringProperty residenceCity = new SimpleStringProperty(this,"residenceCity");
   private final StringProperty address = new SimpleStringProperty(this,"address");
   private final StringProperty zone = new SimpleStringProperty(this,"zone");
   private final StringProperty civilState = new SimpleStringProperty(this,"civilState");
   public Conductores(){}
   public Conductores(Long id){
      setId(id);
   }
   public Conductores(
           Long id,
           Long idLicencia,
           String name,
           String lastName,
           String cellphone,
           String birthDate,
           String email,
           String licenseCategory,
           String turn,
           String residenceCity,
           String address,
           String zone,
           String civilState) {
      setId(id);
      setIdLicencia(idLicencia);
      setName(name);
      setLastName(lastName);
      setCellphone(cellphone);
      setBirthDate(birthDate);
      setEmail(email);
      setLicenseCategory(licenseCategory);
      setTurn(turn);
      setResidenceCity(residenceCity);
      setAddress(address);
      setZone(zone);
      setCivilState(civilState);
   }

   public long getIdLicencia() {
      return idLicencia.get();
   }

   public LongProperty idLicenciaProperty() {
      return idLicencia;
   }

   public void setIdLicencia(long idLicencia) {
      this.idLicencia.set(idLicencia);
   }

   public String getName() {
      return name.get();
   }

   public StringProperty nameProperty() {
      return name;
   }

   public void setName(String name) {
      this.name.set(name);
   }

   public String getLastName() {
      return lastName.get();
   }

   public StringProperty lastNameProperty() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName.set(lastName);
   }

   public String getPhone() {
      return phone.get();
   }

   public StringProperty phoneProperty() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone.set(phone);
   }

   public String getCellphone() {
      return cellphone.get();
   }

   public StringProperty cellphoneProperty() {
      return cellphone;
   }

   public void setCellphone(String cellphone) {
      this.cellphone.set(cellphone);
   }

   public String getBirthDate() {
      return birthDate.get();
   }

   public StringProperty birthDateProperty() {
      return birthDate;
   }

   public void setBirthDate(String birthDate) {
      this.birthDate.set(birthDate);
   }

   public String getEmail() {
      return email.get();
   }

   public StringProperty emailProperty() {
      return email;
   }

   public void setEmail(String email) {
      this.email.set(email);
   }

   public String getLicenseCategory() {
      return licenseCategory.get();
   }

   public StringProperty licenseCategoryProperty() {
      return licenseCategory;
   }

   public void setLicenseCategory(String licenseCategory) {
      this.licenseCategory.set(licenseCategory);
   }

   public String getTurn() {
      return turn.get();
   }

   public StringProperty turnProperty() {
      return turn;
   }

   public void setTurn(String turn) {
      this.turn.set(turn);
   }

   public String getResidenceCity() {
      return residenceCity.get();
   }

   public StringProperty residenceCityProperty() {
      return residenceCity;
   }

   public void setResidenceCity(String residenceCity) {
      this.residenceCity.set(residenceCity);
   }

   public String getAddress() {
      return address.get();
   }

   public StringProperty addressProperty() {
      return address;
   }

   public void setAddress(String address) {
      this.address.set(address);
   }

   public String getZone() {
      return zone.get();
   }

   public StringProperty zoneProperty() {
      return zone;
   }

   public void setZone(String zone) {
      this.zone.set(zone);
   }

   public String getCivilState() {
      return civilState.get();
   }

   public StringProperty civilStateProperty() {
      return civilState;
   }

   public void setCivilState(String civilState) {
      this.civilState.set(civilState);
   }

   public long getId() {
      return id.get();
   }

   public LongProperty idProperty() {
      return id;
   }

   public void setId(Long id) {
      this.id.set(id);
   }

   public boolean GuardarConductor(){
      try
      {
         cx= new Conexion();
         con= cx.getConexion();
         PreparedStatement stmt = con.prepareStatement("INSERT INTO tbl_conductores (id_Conductor, id_licencia_Conductor,name_Conductor, last_name_Conductor, phone_Conductor, cellphone_Conductor, birth_date_Conductor, email_Conductor, licence_category_Conductor, turn_Conductor, residence_city_Conductor , address_Conductor, zone_Conductor, civil_state_Conductor) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

         stmt.setString(1, String.valueOf(this.getId()));
         stmt.setString(2, String.valueOf(this.getIdLicencia()));
         stmt.setString(3,this.getName());
         stmt.setString(4,this.getLastName());
         stmt.setString(5,this.getPhone());
         stmt.setString(6,this.getCellphone());
         stmt.setString(7,this.getBirthDate());
         stmt.setString(8,this.getEmail());
         stmt.setString(9,this.getLicenseCategory());
         stmt.setString(10,this.getTurn());
         stmt.setString(11,this.getResidenceCity());
         stmt.setString(12,this.getAddress());
         stmt.setString(13,this.getZone());
         stmt.setString(14,this.getCivilState());
         //stmt.setString(15,this.codigovehiculo);
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
         PreparedStatement stmt = con.prepareStatement("UPDATE tbl_conductores SET name_Conductor = '" +
                 this.getName() + "', last_name_Conductor = '" + this.getLastName() + "', phone_Conductor='" + this.getPhone() + "', cellphone_Conductor='" + this.getCellphone() +
                 "', birth_date_Conductor = '" + this.getBirthDate() + " ', email_Conductor = '" + this.getEmail() + "', licence_category_Conductor ='"+ this.getLicenseCategory() + "', id_licencia_Conductor ='"+this.getIdLicencia() + "', turn_Conductor = '" + this.getTurn() +"', residence_city_Conductor= '" + this.getResidenceCity() + "', address_Conductor ='"+ this.getAddress() +"', zone_Conductor ='" +this.getZone()+"', civil_state_Conductor = '" + this.getCivilState() + "' WHERE (id_Conductor = " + this.getId() + ")");
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
   public static ArrayList<Conductores> consultarTodos(){
      ArrayList<Conductores> resultList = new ArrayList<>();
      try{
         cx = new Conexion();
         con = cx.getConexion();
         Statement statement = con.createStatement();
         ResultSet rs = statement.executeQuery("SELECT * FROM tbl_conductores");
         while (rs.next()){
            Conductores actual_row = new Conductores();
            actual_row.setId(Long.parseLong(rs.getString("id_conductor")));
            actual_row.setIdLicencia(Long.parseLong(rs.getString("id_Licencia_Conductor")));
            actual_row.setName(rs.getString("name_Conductor"));
            actual_row.setLastName(rs.getString("last_name_Conductor"));
            actual_row.setPhone(rs.getString("phone_Conductor"));
            actual_row.setCellphone(rs.getString("cellphone_Conductor"));
            actual_row.setBirthDate(rs.getString("birth_date_Conductor"));
            actual_row.setEmail(rs.getString("email_Conductor"));
            actual_row.setLicenseCategory(rs.getString("licence_category_Conductor"));
            actual_row.setTurn(rs.getString("turn_Conductor"));
            actual_row.setResidenceCity(rs.getString("residence_city_Conductor"));
            actual_row.setAddress(rs.getString("address_Conductor"));
            actual_row.setCivilState(rs.getString("civil_state_Conductor"));
            actual_row.setZone(rs.getString("zone_Conductor"));
            resultList.add(actual_row);
         }
      }catch (Exception e){
         System.out.println(e.getMessage());
         return null;
      }
      return resultList;
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
            this.setId(Long.parseLong(rs.getString("id_conductor")));
            this.setIdLicencia(Long.parseLong(rs.getString("id_Licencia_Conductor")));
            this.setName(rs.getString("name_Conductor"));
            this.setLastName(rs.getString("last_name_Conductor"));
            this.setPhone(rs.getString("phone_Conductor"));
            this.setCellphone(rs.getString("cellphone_Conductor"));
            this.setBirthDate(rs.getString("birth_date_Conductor"));
            this.setEmail(rs.getString("email_Conductor"));
            this.setLicenseCategory(rs.getString("licence_category_Conductor"));
            this.setTurn(rs.getString("turn_Conductor"));
            this.setResidenceCity(rs.getString("residence_city_Conductor"));
            this.setAddress(rs.getString("address_Conductor"));
            this.setCivilState(rs.getString("civil_state_Conductor"));
            this.setZone(rs.getString("zone_Conductor"));
            //this.codigovehiculo = "12";

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
   public static boolean EliminarConductor(long IdentificacionABuscar){
      try {
         cx = new Conexion();
         con = cx.getConexion();
         PreparedStatement stmt = con.prepareStatement("DELETE FROM tbl_conductores WHERE (id_Conductor = " + IdentificacionABuscar + ")");
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










