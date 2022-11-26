package com.sa.programacionaplicada.presentacion.conductor;/*Author:sfeli*/

import com.sa.programacionaplicada.HelloApplication;
import com.sa.programacionaplicada.logica.ConductorLicenseCategory;
import com.sa.programacionaplicada.logica.Conductores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchAnyConductoresController implements Initializable {
    @FXML
    private CheckBox searchById;
    @FXML
    private TextField idField;
    @FXML
    private CheckBox searchByName;
    @FXML
    private TextField nameField;
    @FXML
    private CheckBox searchByLastName;
    @FXML
    private TextField lastNameField;
    @FXML
    private CheckBox searchByLicenseCategory;
    @FXML
    private ChoiceBox<ConductorLicenseCategory> categoryLicenceChoiceBox;
    @FXML
    private CheckBox searchByIdVehiculo;
    @FXML
    private TextField idVehiculoField;
    @FXML
    private CheckBox searchByResidenceCityAndZone;
    @FXML
    private TextField residenceCityField;
    @FXML
    private TextField zoneField;

    private final Conductores result = new Conductores();
    private final Dialog<String> resultDialog = new Dialog<>();
    private SearchResultConductorController resultController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryLicenceChoiceBox.getItems().addAll(ConductorLicenseCategory.B1,ConductorLicenseCategory.B2);
        resultDialog.initModality(Modality.NONE);
        resultDialog.setTitle("Resultado de la Busqueda");
        resultDialog.getDialogPane().getButtonTypes().add(new ButtonType("Cerrar", ButtonBar.ButtonData.OK_DONE));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("conductor-views/search-result-view.fxml"));
        try {
            resultDialog.getDialogPane().setContent(fxmlLoader.load());
            resultController = fxmlLoader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void doSearch(ActionEvent actionEvent) {
        if (searchById.isSelected()){
            if (result.ConsultarConductorPorId(Long.parseLong(idField.getText()))){
                resultController.fillView(result);
                resultDialog.show();
            }
        }else
        if (searchByName.isSelected()){
            if (result.ConsultarConductorPorNombre(nameField.getText())){
                resultController.fillView(result);
                resultDialog.show();
            }
        }else
        if (searchByLastName.isSelected()){
            if (result.ConsultarConductorPorApellido(lastNameField.getText())){
                resultController.fillView(result);
                resultDialog.show();
            }
        }else
        if(searchByLicenseCategory.isSelected()){
            if (result.ConsultarConductorPorCategoriaLicencia(categoryLicenceChoiceBox.getValue().toString())){
                resultController.fillView(result);
                resultDialog.show();
            }
        }else
        if(searchByResidenceCityAndZone.isSelected()){
            if (result.ConsultarConductorPorCiudadYBarrio(residenceCityField.getText(), zoneField.getText())){
                resultController.fillView(result);
                resultDialog.show();
            }
        }
        else {
            System.out.println("No encontrado");
        }
    }
}
