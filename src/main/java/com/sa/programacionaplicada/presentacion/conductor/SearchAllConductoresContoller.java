package com.sa.programacionaplicada.presentacion.conductor;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.DummyConductor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class SearchAllConductoresContoller implements Initializable {

    @FXML
    private TableView<DummyConductor> table;
    @FXML
    private TableColumn<DummyConductor,Long> id;
    @FXML
    private TableColumn<DummyConductor,Long> idLicencia;
    @FXML
    private TableColumn<DummyConductor,String> name;
    @FXML
    private TableColumn<DummyConductor,String> lastName;
    @FXML
    private TableColumn<DummyConductor, String> phone;
    @FXML
    private TableColumn<DummyConductor, String> cellphone;
    @FXML
    private TableColumn<DummyConductor, String> birthDate;
    @FXML
    private TableColumn<DummyConductor, String> email;
    @FXML
    private TableColumn<DummyConductor, String> licenceCategory;
    @FXML
    private TableColumn<DummyConductor, String> turn;
    @FXML
    private TableColumn<DummyConductor, String> residenceCity;
    @FXML
    private TableColumn<DummyConductor, String> address;
    @FXML
    private TableColumn<DummyConductor, String> zone;
    @FXML
    private TableColumn<DummyConductor, String> civilState;

    private final ObservableList<DummyConductor> conductors = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conductors.add(new DummyConductor(1L));
        setupColumns();
        table.setItems(conductors);

    }

    private void setupColumns(){
        id.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).idProperty().getName()));
        idLicencia.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).idLicenciaProperty().getName()));
        name.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).nameProperty().getName()));
        lastName.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).lastNameProperty().getName()));
        phone.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).phoneProperty().getName()));
        cellphone.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).cellphoneProperty().getName()));
        birthDate.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).birthDateProperty().getName()));
        email.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).emailProperty().getName()));
        licenceCategory.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).licenceCategoryProperty().getName()));
        turn.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).turnProperty().getName()));
        residenceCity.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).residenceCityProperty().getName()));
        address.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).addressProperty().getName()));
        zone.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).zoneProperty().getName()));
        civilState.setCellValueFactory(new PropertyValueFactory<>(conductors.get(0).civilStateProperty().getName()));

    }
}
