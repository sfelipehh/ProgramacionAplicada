package com.sa.programacionaplicada.presentacion.conductor;/*Author:sfeli*/

import com.jfoenix.controls.JFXButton;
import com.sa.programacionaplicada.logica.ConductorCivilState;
import com.sa.programacionaplicada.logica.ConductorLicenseCategory;
import com.sa.programacionaplicada.logica.ConductorTurn;
import com.sa.programacionaplicada.logica.Conductores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyAnyConductoresController implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ChoiceBox<ConductorLicenseCategory> categoryLicenceChoiceBox;
    @FXML
    private TextField idVehiculoField;
    @FXML
    private TextField residenceCityField;
    @FXML
    private TextField zoneField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField cellphoneField;
    @FXML
    private TextField birthdateField;
    @FXML
    private TextField emailField;
    @FXML
    private ChoiceBox<ConductorTurn> turnChoiceBox;
    @FXML
    private ChoiceBox<ConductorCivilState> civilstateChoiceBox;
    @FXML
    private TextField addressField;
    @FXML
    private JFXButton modificationButton;
    private final Conductores object = new Conductores();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryLicenceChoiceBox.getItems().addAll(ConductorLicenseCategory.B1,ConductorLicenseCategory.B2);
        turnChoiceBox.getItems().addAll(ConductorTurn.DIA,ConductorTurn.NOCHE);
        civilstateChoiceBox.getItems().addAll(ConductorCivilState.SOL,ConductorCivilState.NOSOL);
    }

    @FXML
    private void doSearch(ActionEvent actionEvent) {
        if (!idField.getText().equals("")){
            if (object.ConsultarConductor(Long.parseLong(idField.getText()))){
                fillView();
            }
        }
    }
    private void fillView(){
        nameField.setText(object.getNombre());
        nameField.setDisable(false);
        lastNameField.setText(object.getApellido());
        lastNameField.setDisable(false);
        categoryLicenceChoiceBox.setValue(ConductorLicenseCategory.valueOf(object.getCategorialicencia()));
        categoryLicenceChoiceBox.setDisable(false);
        idVehiculoField.setText("K");
        idVehiculoField.setDisable(false);
        residenceCityField.setText(object.getCiudad());
        residenceCityField.setDisable(false);
        zoneField.setText(object.getBarrio());
        zoneField.setDisable(false);
        phoneField.setText(object.getFijo());
        phoneField.setDisable(false);
        cellphoneField.setText(object.getCelular());
        cellphoneField.setDisable(false);
        birthdateField.setText(object.getFecha());
        birthdateField.setDisable(false);
        emailField.setText(object.getCorreo());
        emailField.setDisable(false);
        turnChoiceBox.setValue(ConductorTurn.valueOf(object.getTurno()));
        turnChoiceBox.setDisable(false);
        civilstateChoiceBox.setValue(ConductorCivilState.valueOf(object.getEstadocivil()));
        civilstateChoiceBox.setDisable(false);
        addressField.setText(object.getDireccion());
        addressField.setDisable(false);
        modificationButton.setDisable(false);
    }
    @FXML
    private void doModification(ActionEvent actionEvent) {
        object.setNombre(nameField.getText());
        object.setApellido(lastNameField.getText());
        object.setCategorialicencia(categoryLicenceChoiceBox.getValue().toString());
        object.setCiudad(residenceCityField.getText());
        object.setBarrio(zoneField.getText());
        object.setFijo(phoneField.getText());
        object.setCelular(cellphoneField.getText());
        object.setFecha(birthdateField.getText());
        object.setCorreo(emailField.getText());
        object.setTurno(turnChoiceBox.getValue().toString());
        object.setEstadocivil(civilstateChoiceBox.getValue().toString());
        object.setDireccion(addressField.getText());
        object.ActualizarConductor();
    }
}
