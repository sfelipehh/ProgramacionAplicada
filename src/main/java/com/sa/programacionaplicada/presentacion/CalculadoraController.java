package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculadoraController implements Initializable {
    @FXML
    CalculatorBaseController calculatorController;
    @FXML
    TitledPane containerPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        containerPane.setText(calculatorController.getCalculatorName() + "       ");
    }
}
