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
    private TextField idLicenseField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ChoiceBox<ConductorLicenseCategory> categoryLicenseChoiceBox;
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
        categoryLicenseChoiceBox.getItems().addAll(ConductorLicenseCategory.B1,ConductorLicenseCategory.B2);
        turnChoiceBox.getItems().addAll(ConductorTurn.DIA,ConductorTurn.NOCHE);
        civilstateChoiceBox.getItems().addAll(ConductorCivilState.SOL,ConductorCivilState.NOSOL);
    }
    public void reset(){
        idField.setText(null);
        modificationButton.setOnAction(this::doModification);
    }
    @FXML
    private void doSearch(ActionEvent actionEvent) {
        if (!idField.getText().equals("")){
            if (object.ConsultarConductorPorId(Long.parseLong(idField.getText()))){
                fillView();
            }else {
                setEnabled();
                modificationButton.setOnAction(this::doCreation);
            }
        }
    }
    private void fillView(){
        idLicenseField.setText(String.valueOf(object.getIdLicencia()));
        idLicenseField.setDisable(false);
        nameField.setText(object.getName());
        nameField.setDisable(false);
        lastNameField.setText(object.getLastName());
        lastNameField.setDisable(false);
        categoryLicenseChoiceBox.setValue(ConductorLicenseCategory.valueOf(object.getLicenseCategory()));
        categoryLicenseChoiceBox.setDisable(false);
        idVehiculoField.setText("K");
        idVehiculoField.setDisable(false);
        residenceCityField.setText(object.getResidenceCity());
        residenceCityField.setDisable(false);
        zoneField.setText(object.getZone());
        zoneField.setDisable(false);
        phoneField.setText(object.getPhone());
        phoneField.setDisable(false);
        cellphoneField.setText(object.getCellphone());
        cellphoneField.setDisable(false);
        birthdateField.setText(object.getBirthDate());
        birthdateField.setDisable(false);
        emailField.setText(object.getEmail());
        emailField.setDisable(false);
        turnChoiceBox.setValue(ConductorTurn.valueOf(object.getTurn()));
        turnChoiceBox.setDisable(false);
        civilstateChoiceBox.setValue(ConductorCivilState.valueOf(object.getCivilState()));
        civilstateChoiceBox.setDisable(false);
        addressField.setText(object.getAddress());
        addressField.setDisable(false);
        modificationButton.setDisable(false);
    }
    private void setEnabled(){
        idLicenseField.setDisable(false);
        nameField.setDisable(false);
        lastNameField.setDisable(false);
        categoryLicenseChoiceBox.setDisable(false);
        idVehiculoField.setDisable(false);
        residenceCityField.setDisable(false);
        zoneField.setDisable(false);
        phoneField.setDisable(false);
        cellphoneField.setDisable(false);
        birthdateField.setDisable(false);
        emailField.setDisable(false);
        turnChoiceBox.setDisable(false);
        civilstateChoiceBox.setDisable(false);
        addressField.setDisable(false);
        modificationButton.setDisable(false);
    }
    @FXML
    private void doModification(ActionEvent actionEvent) {
        object.setIdLicencia(Long.parseLong(idLicenseField.getText()));
        object.setName(nameField.getText());
        object.setLastName(lastNameField.getText());
        object.setLicenseCategory(categoryLicenseChoiceBox.getValue().toString());
        object.setResidenceCity(residenceCityField.getText());
        object.setZone(zoneField.getText());
        object.setPhone(phoneField.getText());
        object.setCellphone(cellphoneField.getText());
        object.setBirthDate(birthdateField.getText());
        object.setEmail(emailField.getText());
        object.setTurn(turnChoiceBox.getValue().toString());
        object.setCivilState(civilstateChoiceBox.getValue().toString());
        object.setAddress(addressField.getText());
        object.ActualizarConductor();
    }
    private void doCreation(ActionEvent actionEvent){
        object.setId(Long.parseLong(idField.getText()));
        object.setIdLicencia(Long.parseLong(idLicenseField.getText()));
        object.setName(nameField.getText());
        object.setLastName(lastNameField.getText());
        object.setLicenseCategory(categoryLicenseChoiceBox.getValue().toString());
        object.setResidenceCity(residenceCityField.getText());
        object.setZone(zoneField.getText());
        object.setPhone(phoneField.getText());
        object.setCellphone(cellphoneField.getText());
        object.setBirthDate(birthdateField.getText());
        object.setEmail(emailField.getText());
        object.setTurn(turnChoiceBox.getValue().toString());
        object.setCivilState(civilstateChoiceBox.getValue().toString());
        object.setAddress(addressField.getText());
        if (object.GuardarConductor()){
            System.out.println("Conductor guardado");
        }else {
            System.out.println("No guardado");
        }
    }
}
