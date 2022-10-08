package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
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
        setButtonsOut((byte) -1);
        inputType.selectToggle(DECButton);
        stateProperty.addListener(showResult());
        ObservableList<Node> keysButtons = keyboardGridPane.getChildren();
        for (Node button : keysButtons) {
            ((ButtonBase) button).setOnAction(this::getButtonAction);
        }
        memoryLabel.setText(null);
        actualLabel.setText("0");
        System.out.println(keyboardGridPane.getChildren().equals(keysButtons));
        System.out.println(checkInput("123"));
    }

    private ChangeListener<CalculationState> showResult(){
        return (observableValue, calculationState, t1) -> {
            if (calculationState == CalculationState.FIRST_INPUT && t1 == CalculationState.ACTION_PERFORMED){
                actualLabel.setText(memory[2]);
                try {
                    memory[0] = checkInput(memory[2]);
                }catch (NumberFormatException e){
                    System.out.println("Entrada invalida!");
                }
                stateProperty.set(CalculationState.FIRST_INPUT);
            }
            if (t1 == CalculationState.ACTION_CHOOSE || t1 == CalculationState.ACTION_PERFORMED){
                System.out.println(memoryStringBuilder);
            }
        };
    }

    private String checkInput(String inputString) throws NumberFormatException{
        if (inputString.contains(".") || inputString.contains(",")){
            throw new NumberFormatException("La entrada tiene un punto o coma");
        }
        if(!inputType.getSelectedToggle().equals(BINButton) && inputString.matches("[0-1]++")){
            throw new NumberFormatException("La entrada es Bin");
        }
        if (!inputType.getSelectedToggle().equals(HEXButton) && inputString.matches("0x[A-F\\d]++")){
            throw new NumberFormatException("La entrada es Hex");
        }
        if (!inputType.getSelectedToggle().equals(OCTButton) && inputString.matches("ox[0-7]++")){
            throw new NumberFormatException("La entrada es Oct");
        }
        return inputString;
    }
    private void getButtonAction(ActionEvent actionEvent){

    }

    private int castToUnsignedByte(int number){
        return number & 0xFF;
    }
    private void setButtonsOut(int number){
        int newNumber = castToUnsignedByte(number);
        HEXButton.setText("%02X".formatted(newNumber));
        DECButton.setText("%03d".formatted(newNumber));
        OCTButton.setText("%03o".formatted(newNumber));
        BINButton.setText("%8s".formatted(Integer.toBinaryString(newNumber + 0x100)).substring(1));
    }
}
