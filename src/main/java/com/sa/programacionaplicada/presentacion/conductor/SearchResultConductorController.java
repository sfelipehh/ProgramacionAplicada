package com.sa.programacionaplicada.presentacion.conductor;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.Conductores;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SearchResultConductorController {
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
        idLabel.setText(String.valueOf(conductor.getIdentificacion()));
        nameLabel.setText(conductor.getNombre());
        lastNameLabel.setText(conductor.getApellido());
        categoryLicenceLabel.setText(conductor.getCategorialicencia());
        //todo. idVehiculoLabel.setText("");
        residenceCityLabel.setText(conductor.getCiudad());
        zoneLabel.setText(conductor.getBarrio());
    }
}
