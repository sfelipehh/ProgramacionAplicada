package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgramadorController extends CalculatorBaseController{
    @FXML
    private Label memoryLabel;
    @FXML
    private Label actualLabel;
    @FXML
    private GridPane keyboardGridPane;
    @FXML
    private ToggleGroup inputType;
    @FXML
    private ToggleButton HEXButton;
    @FXML
    private ToggleButton DECButton;
    @FXML
    private ToggleButton OCTButton;
    @FXML
    private ToggleButton BINButton;
    @Override
    public String getCalculatorName() {
        return "Programador";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonsOut((byte) -127);
    }

    private void setButtonsOut(byte number){
        HEXButton.setText("%02X".formatted(number));
        DECButton.setText("%03d".formatted(number));
        OCTButton.setText("%03o".formatted(number));
        BINButton.setText(Integer.toBinaryString(number));
    }
}
