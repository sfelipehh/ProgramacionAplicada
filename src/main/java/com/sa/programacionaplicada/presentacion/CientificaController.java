package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CientificaController extends CalculatorBaseController {
    @FXML
    private Label memoryLabel;
    @FXML
    private Label actualLabel;
    @FXML
    private GridPane keyboardGridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stateProperty.addListener(showResult());
        ObservableList<Node> keysButtons = keyboardGridPane.getChildren();
        for (Node button : keysButtons) {
            ((ButtonBase) button).setOnAction(this::getButtonAction);
        }
        memoryLabel.setText(null);
        actualLabel.setText("0");
        System.out.println(keyboardGridPane.getChildren().equals(keysButtons));
    }
    private ChangeListener<CalculationState> showResult(){
        return (observableValue, old, t1) -> {
          if (t1 == CalculationState.ACTION_CHOOSE){
              memoryLabel.setText(memoryStringBuilder.toString());
          }
          if (t1 == CalculationState.ACTION_PERFORMED){
              renewActual(memory[2]);
              memory[0] = checkInput(memory[2]);
              CientificaController.this.stateProperty.set(CalculationState.FIRST_INPUT);
          }
        };
    }
    private void updateActual(String any){
        if (any.equals("-D")){
            actualStringBuilder.deleteCharAt(actualStringBuilder.length()-1);
        }else actualStringBuilder.append(any);
        actualLabel.setText(actualStringBuilder.toString());
    }
    private void renewActual(String any){
        actualStringBuilder.replace(0,actualStringBuilder.length(), any);
        actualLabel.setText(actualStringBuilder.toString());
    }
    private String checkInput(String inputString){
        return inputString.replace(",",".");
    }
    private void doInput(String inputNumber) {
        int calculationStateOrdinal = stateProperty.get().ordinal();
        if (!memory[calculationStateOrdinal].equals("")){
            renewActual(inputNumber);
        }else updateActual(inputNumber);
        memory[calculationStateOrdinal] = memory[calculationStateOrdinal] + inputNumber;
    }
    private void undoInput(){
        int calculationStateOrdinal = stateProperty.get().ordinal();
        updateActual("-D");
        String actual = memory[calculationStateOrdinal];
        memory[calculationStateOrdinal] = actual.substring(0,actual.length()-1);
    }
    private void onActionChosen(String actionSymbol){
        onActionChosen(actionSymbol,false);
    }
    private void onActionChosen(String actionSymbol, boolean oneArg) {
        if (oneArg){
            memoryStringBuilder.append(actionSymbol.formatted(memory[0]));
        }else {
            memoryStringBuilder.append(actionSymbol);
            stateProperty.set(CalculationState.SECOND_INPUT);
        }
        stateProperty.set(CalculationState.ACTION_CHOOSE);
    }
    private void getButtonAction(ActionEvent actionEvent){
        ButtonCodes buttonCode = ButtonCodes.valueOf(((ButtonBase) actionEvent.getSource()).getId());
        System.out.println(stateProperty);
        double input;
        switch (buttonCode){
            case Op2 -> {
                ToggleButton op2Button = ((ToggleButton) actionEvent.getSource());
                if (!op2Button.isSelected()){
                    op2Button.getStyleClass().add("special");
                }else {
                    op2Button.getStyleClass().remove("special");
                }
            }
            case Pow2 -> {
                if (!memory[0].equals("")) {
                    actionChoosen = (Math::pow);
                    memory[1] = "2";
                    onActionChosen(buttonCode.getSymbol(),true);
                }
                stateProperty.set(CalculationState.ACTION_CHOOSE);
            }
            case Root2 -> {
            }
            case yPow -> {
            }
            case x10Pow -> {
            }
            case Log10 -> {
            }
            case eLog -> {
            }
            case piConstant -> {
            }
            case inverso -> {
            }
            case parentesisOpen -> {
            }
            case seven -> {
            }
            case four -> {
            }
            case one -> {
                doInput("1");
            }
            case eConstant -> {
            }
            case abs -> {
            }
            case parentesisClose -> {
            }
            case eight -> {
            }
            case five -> {
            }
            case two -> {
            }
            case cero -> {
            }
            case clear -> {
                memoryStringBuilder.delete(0, memoryStringBuilder.length()-1);
                memoryLabel.setText(null);
            }
            case exp10 -> {
            }
            case factorial -> {
                if (!memory[0].equals("")) {
                    onActionChosen(buttonCode.getSymbol(), true);
                    //actionChoosen = (aDouble, aDouble2) -> logica.factorial(aDouble);
                }
            }
            case nine -> {
                doInput("9");
            }
            case six -> {
                doInput("6");
            }
            case three -> {
                doInput("3");
            }
            case delete -> undoInput();
            case mod -> {
                onActionChosen(buttonCode.getSymbol());
                actionChoosen = ((aDouble, aDouble2) -> aDouble % aDouble2);
            }
            case division -> {
                onActionChosen(buttonCode.getSymbol());
                actionChoosen = ((aDouble, aDouble2) -> aDouble / aDouble2);
            }
            case multiply -> {
                onActionChosen(buttonCode.getSymbol());
                actionChoosen = ((aDouble, aDouble2) -> aDouble * aDouble2);
            }
            case less -> {
                onActionChosen(buttonCode.getSymbol());
                actionChoosen = ((aDouble, aDouble2) -> aDouble - aDouble2);
            }
            case add -> {
                onActionChosen(buttonCode.getSymbol());
                actionChoosen = (Double::sum);
            }
            case equal -> {
                double firstArg = Double.parseDouble(memory[0]);
                double secondArg = Double.parseDouble(memory[1]);
                double calcutation = actionChoosen.apply(firstArg,secondArg);
                memory[2] = "%.3f".formatted(calcutation);
                stateProperty.set(CalculationState.ACTION_PERFORMED);
            }
        }
    }

    @Override
    public String getCalculatorName(){
        return "Científica";
    }
}
