package com.sa.programacionaplicada.presentacion.conductor;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.ConductorLicenseCategory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchAnyConductoresController implements Initializable {

    @FXML
    private ChoiceBox<ConductorLicenseCategory> categoryLicenceChoiceBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryLicenceChoiceBox.getItems().addAll(ConductorLicenseCategory.B1,ConductorLicenseCategory.B2);
    }
}
