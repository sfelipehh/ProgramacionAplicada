package com.sa.programacionaplicada.presentacion.conductor;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.Conductores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchAllConductoresContoller implements Initializable {

    @FXML
    private TableView<Conductores> table;
    @FXML
    private TableColumn<Conductores,Long> id;
    @FXML
    private TableColumn<Conductores,Long> idLicencia;
    @FXML
    private TableColumn<Conductores, Long> idVehiculo;
    @FXML
    private TableColumn<Conductores,String> name;
    @FXML
    private TableColumn<Conductores,String> lastName;
    @FXML
    private TableColumn<Conductores, String> phone;
    @FXML
    private TableColumn<Conductores, String> cellphone;
    @FXML
    private TableColumn<Conductores, String> birthDate;
    @FXML
    private TableColumn<Conductores, String> email;
    @FXML
    private TableColumn<Conductores, String> licenceCategory;
    @FXML
    private TableColumn<Conductores, String> turn;
    @FXML
    private TableColumn<Conductores, String> residenceCity;
    @FXML
    private TableColumn<Conductores, String> address;
    @FXML
    private TableColumn<Conductores, String> zone;
    @FXML
    private TableColumn<Conductores, String> civilState;

    private final ObservableList<Conductores> conductoress = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conductoress.add(new Conductores(1L));
        setupColumns();
        conductoress.remove(0);
        conductoress.addAll(Objects.requireNonNull(Conductores.consultarTodos()));
        table.setItems(conductoress);

    }
    public void searchAll(){
        conductoress.clear();
        conductoress.addAll(Objects.requireNonNull(Conductores.consultarTodos()));
    }
    private void setupColumns(){
        id.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).idProperty().getName()));
        idLicencia.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).idLicenciaProperty().getName()));
        idVehiculo.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).idVehiculoProperty().getName()));
        name.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).nameProperty().getName()));
        lastName.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).lastNameProperty().getName()));
        phone.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).phoneProperty().getName()));
        cellphone.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).cellphoneProperty().getName()));
        birthDate.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).birthDateProperty().getName()));
        email.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).emailProperty().getName()));
        licenceCategory.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).licenseCategoryProperty().getName()));
        turn.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).turnProperty().getName()));
        residenceCity.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).residenceCityProperty().getName()));
        address.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).addressProperty().getName()));
        zone.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).zoneProperty().getName()));
        civilState.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).civilStateProperty().getName()));

    }
}
