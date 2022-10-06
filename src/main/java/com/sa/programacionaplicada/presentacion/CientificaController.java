package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    private ObservableList<Node> keysButtons;
    enum ButtonCodes{
        Op2(""),
        Pow2("%.4f^2"),
        Root2("√%.4f"),
        yPow("%.4f^%.4f"),
        x10Pow("10^%.4f"),
        Log10("log(%.4f)"),
        eLog("ln(%.4f)"),
        piConstant("\uD835\uDED1"),
        inverso("1/%.4f"),
        parentesisOpen("("),
        seven("\uD835\uDFF3"),
        four("\uD835\uDFF0"),
        one("\uD835\uDFED"),
        sign("∓"),
        eConstant("e"),
        abs("|%.4f|"),
        parentesisClose(")"),
        eight("\uD835\uDFF4"),
        five("\uD835\uDFF1"),
        two("\uD835\uDFEE"),
        cero("\uD835\uDFEC"),
        clear(""),
        exp10(",e+%.4f"),
        factorial("fact(%.4f)"),
        nine("\uD835\uDFF5"),
        six("\uD835\uDFF2"),
        three("\uD835\uDFEF"),
        floatPoint(","),
        delete(""),
        mod(" Mod "),
        division(" ÷ "),
        multiply(" × "),
        less(" − "),
        add(" + "),
        equal(" = ");
        private final String symbol;
        ButtonCodes(String symbol){
            this.symbol = symbol;
        }
        public String getSymbol(){
            return symbol;
        }
    }
    enum CalculationState{
        FIRST_INPUT, SECOND_INPUT, ACTION_CHOOSE, ACTION_PERFORMED;
    }
    private final String[] memory = new String[]{"0", "0", "0"};
    private final ObjectProperty<CalculationState> stateProperty = new SimpleObjectProperty<>(this, "state", CalculationState.FIRST_INPUT);
    private ButtonCodes actionChoosen;
    private final StringBuilder memoryStringBuilder = new StringBuilder();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stateProperty.addListener(showResult());
        keysButtons = keyboardGridPane.getChildren();
        for (Node button : keysButtons) {
            ((ButtonBase) button).setOnAction(this::getButtonAction);
        }
        memoryLabel.setText(null);
        actualLabel.setText("0");
        System.out.println(keyboardGridPane.getChildren().equals(keysButtons));
    }
    private ChangeListener<CalculationState> showResult(){
        return (observableValue, calculationState, t1) -> {
          if (calculationState == CalculationState.FIRST_INPUT && t1 == CalculationState.ACTION_PERFORMED){
              actualLabel.setText(memory[2]);
              memory[0] = checkInput(memory[2]);
              stateProperty.set(CalculationState.FIRST_INPUT);
          }
          if (t1 == CalculationState.ACTION_CHOOSE || t1 == CalculationState.ACTION_PERFORMED){
              System.out.println(memoryStringBuilder);
          }
        };
    }

    private String checkInput(String inputString){
        return inputString.replace(",",".");
    }
    private void getButtonAction(ActionEvent actionEvent){
        ButtonCodes buttonCode = ButtonCodes.valueOf(((ButtonBase) actionEvent.getSource()).getId());
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
                if (!memory[0].equals("")){
                    actionChoosen = buttonCode;
                    input = Double.parseDouble(memory[0]);
                    memoryStringBuilder.append(buttonCode.getSymbol().formatted(input));
                    memoryLabel.setText(memoryStringBuilder.toString());
                    Double pow2 = Math.pow(input,2);
                    memory[2] = "%.3f".formatted(pow2);
                }
                stateProperty.set(CalculationState.ACTION_PERFORMED);
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
            }
            case sign -> {
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
                memoryStringBuilder.delete(0, memoryStringBuilder.length());
                memoryLabel.setText(null);
            }
            case exp10 -> {
            }
            case factorial -> {
            }
            case nine -> {
            }
            case six -> {
            }
            case three -> {
            }
            case floatPoint -> {
            }
            case delete -> {
            }
            case mod -> {
            }
            case division -> {
            }
            case multiply -> {
            }
            case less -> {
            }
            case add -> {
            }
            case equal -> {
                stateProperty.set(CalculationState.ACTION_PERFORMED);
            }
        }
    }
    @Override
    public String getCalculatorName(){
        return "Científica";
    }
}
