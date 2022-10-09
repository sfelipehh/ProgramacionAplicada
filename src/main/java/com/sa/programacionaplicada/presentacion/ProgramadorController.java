package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.OperacionesBasicas;
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

    private final OperacionesBasicas logica = new OperacionesBasicas();

    private void onActionChosen(String actionSymbol){
        onActionChosen(actionSymbol,false);
    }
    private void onActionChosen(String actionSymbol, boolean oneArg) {
        modifyMemoryString("-A", memory[0] + actionSymbol);
        stateProperty.set(CalculationState.ACTION_CHOOSE);
    }

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
            if (!(args.equals("0") && (stateProperty.get() == CalculationState.ACTION_CHOOSE))){
                memoryStringBuilder.append(args);
            }
        }
        if (flag.equals("-CL")){
            memoryStringBuilder.deleteCharAt(memoryStringBuilder.length()-1);
            memoryLabel.setText(memoryStringBuilder.toString());
        }
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
    private void renewActual(String any){
        actualStringBuilder.replace(0,actualStringBuilder.length(), any);
        actualLabel.setText(actualStringBuilder.toString());
    }

    private void updateActual(String any){
        if (any.equals("-D")){
            actualStringBuilder.deleteCharAt(actualStringBuilder.length()-1);
        }else actualStringBuilder.append(any);
        actualLabel.setText(actualStringBuilder.toString());
    }

    private void doInput(String inputNumber) {
        int calculationStateOrdinal = stateProperty.get().ordinal();
        if ((!memory[calculationStateOrdinal].equals("") && calculationStateOrdinal==CalculationState.ACTION_PERFORMED.ordinal())){
            renewActual(inputNumber);
            ProgramadorController.this.stateProperty.set(CalculationState.FIRST_INPUT);
        }else if ( !memory[calculationStateOrdinal].equals("") && calculationStateOrdinal == CalculationState.SECOND_INPUT.ordinal()){
            renewActual(inputNumber);
        }else if (memory[calculationStateOrdinal].equals("0")){
            renewActual(inputNumber);
        } else if (inputNumber.equals("3,141592") || inputNumber.equals("2,718281")){
            renewActual(inputNumber);
        }else updateActual(inputNumber);

        if (memory[calculationStateOrdinal].equals("0") || inputNumber.equals("3,141592") || inputNumber.equals("2,718281")){
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
    private double getInput(int inputIndex){

        if(inputType.getSelectedToggle().equals(DECButton)){
            return Integer.parseInt(memory[inputIndex], 10);
        }

        if(inputType.getSelectedToggle().equals(HEXButton)){
            return Integer.parseInt(memory[inputIndex],10);
        }

        if(inputType.getSelectedToggle().equals(OCTButton)){
            return Integer.parseInt(memory[inputIndex],10);
        }

        if (inputType.getSelectedToggle().equals(BINButton)){
            return Integer.parseInt(memory[inputIndex],10);
        }
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
        return Double.parseDouble(checkInput(memory[inputIndex]));
    }

    private void doClear() {
        modifyMemoryString("-C");
        memory[0] = "0";
        memory[1] = "0";
        memory[2] = "0";
        stateProperty.set(CalculationState.FIRST_INPUT);
        renewActual("0");
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

    private void getButtonAction(ActionEvent actionEvent){
        ButtonCodes buttonCode = ButtonCodes.valueOf(((ButtonBase) actionEvent.getSource()).getId());
        switch(buttonCode){

            case seven -> doInput("7");
            case four -> doInput("4");
            case one -> doInput("1");
            case sign -> doInput("-");
            case eight -> doInput("8");
            case five -> doInput("5");
            case two -> doInput("2");
            case cero -> doInput("0");
            case nine -> doInput("9");
            case six -> doInput("6");
            case three -> doInput("3");

            case floatPoint -> doInput(",");
            case eConstant -> doInput("2,718281");
            case piConstant -> doInput("3,141592");

            case add -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen=logica::sumar;
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

            case a -> doInput("A");
            case b -> doInput("B");
            case c -> doInput("C");
            case d -> doInput("D");
            case e -> doInput("E");
            case f -> doInput("F");

            case equal -> {
                double firstArg = getInput(0);
                double secondArg = getInput(1);
                modifyMemoryString("-A", memory[1]);
                double calcutation = actionChosen.apply(firstArg,secondArg);
                memory[2] = Double.toString(calcutation);
                stateProperty.set(CalculationState.ACTION_PERFORMED);
            }

            case clear -> doClear();
            case delete -> undoInput();

        }
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
