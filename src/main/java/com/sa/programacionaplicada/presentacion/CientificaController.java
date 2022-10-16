package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.OperacionesMatematicas;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CientificaController extends CalculatorBaseController {
    @FXML
    private Label memoryLabel;
    @FXML
    private Label actualLabel;
    @FXML
    private GridPane keyboardGridPane;
    @FXML
    private MenuButton moreFunctionsButton;

    private final OperacionesMatematicas logica = new OperacionesMatematicas();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stateProperty.addListener(showResult());
        ObservableList<Node> keysButtons = keyboardGridPane.getChildren();
        for (Node button : keysButtons) {
            ((ButtonBase) button).setOnAction(this::getButtonAction);
        }
        for (MenuItem option: moreFunctionsButton.getItems()) {
            option.setOnAction(this::getButtonAction);
        }
        memoryLabel.setText(null);
        actualLabel.setText("0");

    }
    private ChangeListener<CalculationState> showResult(){
        return (observableValue, old, t1) -> {
          if (t1 == CalculationState.ACTION_CHOOSE || t1 == CalculationState.ONE_ARG_ACTION_CHOOSE){
              modifyMemoryString("-P");
          }
          if (t1 == CalculationState.ACTION_CHOOSE){
              stateProperty.set(CalculationState.SECOND_INPUT);
          }
          if (t1 == CalculationState.ACTION_PERFORMED && old != CalculationState.ONE_ARG_ACTION_CHOOSE){
              modifyMemoryString("-A", memory[1]);
          }
          if (t1 == CalculationState.ACTION_PERFORMED) {
              modifyMemoryString("-P");
              renewActual(memory[2]);
              memory[0] = checkInput(memory[2]);
          }
          if(old == CalculationState.ACTION_PERFORMED && t1 == CalculationState.FIRST_INPUT){
              doClear();
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
        int calculationStateOrdinal = stateProperty.get().ordinal() <= 1 ? stateProperty.get().ordinal() : 0;

        if ((!memory[calculationStateOrdinal].equals("") && stateProperty.get().ordinal()==CalculationState.ACTION_PERFORMED.ordinal())){
            CientificaController.this.stateProperty.set(CalculationState.FIRST_INPUT);
            renewActual(inputNumber);
        }else if ( memory[calculationStateOrdinal].equals("") && calculationStateOrdinal == CalculationState.SECOND_INPUT.ordinal()){
            renewActual(inputNumber);
        }else if (memory[calculationStateOrdinal].equals("") || memory[calculationStateOrdinal].equals("0")){
            renewActual(inputNumber);
        } else if (inputNumber.equals("3,141592") || inputNumber.equals("2,718281")){
            renewActual(inputNumber);
        }else updateActual(inputNumber);

        if (memory[calculationStateOrdinal].equals("") || inputNumber.equals("3,141592") || inputNumber.equals("2,718281")){
            memory[calculationStateOrdinal] = inputNumber;
        }else if (inputNumber.equals("-")){
            if (memory[calculationStateOrdinal].charAt(0) == '-'){
                String substring = (String) memory[calculationStateOrdinal].subSequence(1,memory[calculationStateOrdinal].length());
                memory[calculationStateOrdinal] = substring;
                renewActual(substring);

            }else {
                memory[calculationStateOrdinal] = inputNumber + memory[calculationStateOrdinal];
                renewActual(memory[calculationStateOrdinal]);
            }
        }else {
            memory[calculationStateOrdinal] = memory[calculationStateOrdinal] + inputNumber;
        }
    }
    private void undoInput(){
        if (stateProperty.get() != CalculationState.ACTION_PERFORMED){
            int calculationStateOrdinal = stateProperty.get().ordinal();
            updateActual("-D");
            String actual = memory[calculationStateOrdinal];
            try {
                memory[calculationStateOrdinal] = actual.substring(0,actual.length()-1);
            }catch (StringIndexOutOfBoundsException ignored){}
        }
        if(stateProperty.get() == CalculationState.ACTION_CHOOSE){
            actionChosen = null;
            modifyMemoryString("-CL");
        }
    }
    /**
     * @param flag One of -C : Clear | -P : Print
     */
    private void modifyMemoryString(String flag){
        modifyMemoryString(flag,null);
    }
    /**
     * @param flag One of -C : Clear | -P : Print | -A : Append | -CL : Clear Last
     * @param args Arguments for Append
     */
    private void modifyMemoryString(String flag, String args) {
        if (flag.equals("-C")) {
            try {
                memoryStringBuilder.delete(0, memoryStringBuilder.length());
            }catch (StringIndexOutOfBoundsException ignored){}
            memoryLabel.setText(null);
        }
        if (flag.equals("-P")){
            memoryLabel.setText(memoryStringBuilder.toString());
        }
        if (flag.equals("-A") && args!=null){
            memoryStringBuilder.append(args);
        }
        if (flag.equals("-CL")){
            memoryStringBuilder.deleteCharAt(memoryStringBuilder.length()-1);
            memoryLabel.setText(memoryStringBuilder.toString());
        }
    }
    private void onActionChosen(String actionSymbol){
        onActionChosen(actionSymbol,false);
    }
    private void onActionChosen(String actionSymbol, boolean oneArg) {
        if (oneArg){
            modifyMemoryString("-A", actionSymbol.formatted(getInput(0)));
            stateProperty.set(CalculationState.ONE_ARG_ACTION_CHOOSE);
        }else {
            modifyMemoryString("-A", memory[0] + actionSymbol);
            stateProperty.set(CalculationState.ACTION_CHOOSE);
        }
    }
    private double getInput(int inputIndex){
        String input = memory[inputIndex];
        if (input.equals("3,141592")){
            return Math.PI;
        }
        if (input.equals("-3,141592")){
            return Math.PI*-1;
        }
        if (input.equals("2,718281")){
            return Math.E;
        }
        if (input.equals("-2,718281")){
            return Math.E*-1;
        }
        if (input.equals("")){
            return 0;
        }
        return Double.parseDouble(checkInput(memory[inputIndex]));
    }
    private void getButtonAction(ActionEvent actionEvent){
        ButtonCodes buttonCode = null;
        if (actionEvent.getSource() instanceof ButtonBase){
            buttonCode = ButtonCodes.valueOf(((ButtonBase) actionEvent.getSource()).getId());
        }
        if (actionEvent.getSource() instanceof MenuItem){
            buttonCode = ButtonCodes.valueOf(((MenuItem) actionEvent.getSource()).getId());
        }
        switch (Objects.requireNonNull(buttonCode)){
            case Op2 -> {
                ToggleButton op2Button = ((ToggleButton) actionEvent.getSource());
                if (!op2Button.isSelected()){
                    op2Button.getStyleClass().add("special");
                    keyboardGridPane.getChildren().forEach(node -> {
                        String nodeId = node.getId();
                        switch (nodeId){
                            case "Pow2" -> node.setId("Pow3");
                            case "Root2" -> node.setId("Root3");
                            case "yPow" -> node.setId("yRoot");
                        }
                    });
                }else {
                    op2Button.getStyleClass().remove("special");
                    keyboardGridPane.getChildren().forEach(node -> {
                        String nodeId = node.getId();
                        switch (nodeId){
                            case "Pow3" -> node.setId("Pow2");
                            case "Root3" -> node.setId("Root2");
                            case "yRoot" -> node.setId("yPow");
                        }
                    });
                }
            }
            case Pow2 -> {
                if (!memory[0].equals("")) {
                    actionChosen = logica::calcularpotencia;
                    memory[1] = "2";
                    onActionChosen(buttonCode.getSymbol(),true);
                }
            }
            case Root2 -> {
                if (!memory[0].equals("")) {
                    actionChosen = logica::calcularraiz;
                    memory[1] = "2";
                    onActionChosen(buttonCode.getSymbol(),true);
                }
            }
            case yPow -> {
                if (!memory[0].equals("")) {
                    actionChosen = logica::calcularpotencia;
                    onActionChosen(buttonCode.getSymbol(),true);
                }
            }
            case x10Pow -> {
                actionChosen = ((aDouble, aDouble2) -> logica.x10Pow(aDouble));
                onActionChosen(buttonCode.getSymbol(),true);
            }
            case Log10 -> {
                actionChosen = (aDouble, aDouble2) -> logica.logaritmo(aDouble);
                onActionChosen(buttonCode.getSymbol(),true);
            }
            case eLog -> {
                actionChosen =(aDouble, aDouble2) -> logica.logaritmonatural(aDouble);
                onActionChosen(buttonCode.getSymbol(),true);
            }
            case piConstant -> doInput("3,141592");
            case inverso -> {
                actionChosen = (aDouble, aDouble2) -> logica.inverso(aDouble);
                onActionChosen(buttonCode.getSymbol(),true);
            }
            case parentesisOpen -> {
            }
            case seven -> doInput("7");
            case four -> doInput("4");
            case one -> doInput("1");
            case sign -> doInput("-");
            case eConstant -> doInput("2,718281");
            case abs -> {
                actionChosen = (aDouble, aDouble2) -> logica.valorabsoluto(aDouble);
                onActionChosen(buttonCode.getSymbol(),true);
            }
            case parentesisClose -> {
            }
            case eight -> doInput("8");
            case five -> doInput("5");
            case two -> doInput("2");
            case cero -> doInput("0");
            case clear -> doClear();
            case exp10 -> {
            }
            case factorial -> {
                if (!memory[0].equals("")) {
                    actionChosen = (aDouble, aDouble2) -> logica.factorial(aDouble);
                    onActionChosen(buttonCode.getSymbol(), true);
                }
            }
            case nine -> doInput("9");
            case six -> doInput("6");
            case three -> doInput("3");
            case floatPoint -> doInput(",");
            case delete -> undoInput();
            case mod -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::modulo;
            }
            case division -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::dividir;
            }
            case multiply -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::multiplicar;
            }
            case less -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::restar;
            }
            case add -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::sumar;
            }
            case equal -> doCalculation();
            case sen -> {
                if (!memory[0].equals("")) {
                    actionChosen = (aDouble, aDouble2) -> logica.calcularseno(aDouble);
                    onActionChosen(buttonCode.getSymbol(), true);
                }
            }
            case cos -> {
                if (!memory[0].equals("")) {
                    actionChosen = (aDouble, aDouble2) -> logica.calcularcoseno(aDouble);
                    onActionChosen(buttonCode.getSymbol(), true);
                }
            }
            case tan -> {
                if (!memory[0].equals("")) {
                    actionChosen = (aDouble, aDouble2) -> logica.calculartangente(aDouble);
                    onActionChosen(buttonCode.getSymbol(), true);
                }
            }
            case Pow3 -> {
                if (!memory[0].equals("")) {
                    actionChosen = logica::calcularpotencia;
                    memory[1] = "3";
                    onActionChosen(buttonCode.getSymbol(),true);
                }
                stateProperty.set(CalculationState.ACTION_CHOOSE);
            }
            case Root3 -> {
                if (!memory[0].equals("")) {
                    actionChosen = logica::calcularraiz;
                    memory[1] = "3";
                    onActionChosen(buttonCode.getSymbol(),true);
                }
                stateProperty.set(CalculationState.ACTION_CHOOSE);
            }
            case yRoot -> {
                if (!memory[0].equals("")) {
                    actionChosen = logica::calcularraiz;
                    onActionChosen(buttonCode.getSymbol(),true);
                }
            }
            /*case ePow -> {
                if (!memory[0].equals("")) {
                    actionChosen = logica::calcularpotencia;
                    memory[0] = "2,718281";
                    onActionChosen(buttonCode.getSymbol(),true);
                }
            }*/
            default -> throw new IllegalStateException("Unexpected value: " + buttonCode);

        }
    }

    private void doCalculation() {
        double firstArg = getInput(0);
        double secondArg = getInput(1);
        double calcutation = actionChosen.apply(firstArg,secondArg);
        memory[2] = "%.3f".formatted(calcutation);
        stateProperty.set(CalculationState.ACTION_PERFORMED);
    }

    private void doClear() {
        modifyMemoryString("-C");
        memory[0] = "";
        memory[1] = "";
        memory[2] = "";
        stateProperty.set(CalculationState.FIRST_INPUT);
        renewActual("0");
    }

    @Override
    public String getCalculatorName(){
        return "Científica";
    }
}
