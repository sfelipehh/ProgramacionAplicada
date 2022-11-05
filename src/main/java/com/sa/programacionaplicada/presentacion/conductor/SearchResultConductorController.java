package com.sa.programacionaplicada.presentacion.conductor;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.Conductores;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SearchResultConductorController {
    @FXML
    private Label idLicenseLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label cellphoneLabel;
    @FXML
    private Label birthdateLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label turnLabel;
    @FXML
    private Label civilstateLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label categoryLicenceLabel;
    @FXML
    private Label idVehiculoLabel;
    @FXML
    private Label residenceCityLabel;
    @FXML
    private Label zoneLabel;

    public void fillView(Conductores conductor){
        idLabel.setText(String.valueOf(conductor.getId()));
        idLicenseLabel.setText(String.valueOf(conductor.getIdLicencia()));
        nameLabel.setText(conductor.getName());
        lastNameLabel.setText(conductor.getLastName());
        phoneLabel.setText(conductor.getPhone());
        cellphoneLabel.setText(conductor.getCellphone());
        birthdateLabel.setText(conductor.getBirthDate());
        turnLabel.setText(conductor.getTurn());
        civilstateLabel.setText(conductor.getCivilState());
        addressLabel.setText(conductor.getAddress());
        emailLabel.setText(conductor.getEmail());
        categoryLicenceLabel.setText(conductor.getLicenseCategory());
        //todo. idVehiculoLabel.setText("");
        residenceCityLabel.setText(conductor.getResidenceCity());
        zoneLabel.setText(conductor.getZone());
    }
}
