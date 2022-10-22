package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.OperacionesBasicas;
import com.sa.programacionaplicada.logica.OperacionesBitABit;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.BiFunction;

public class ProgramadorController extends CalculatorBaseController{
    @FXML
    private Label memoryLabel;
    @FXML
    private Label actualLabel;
    @FXML
    private GridPane keyboardGridPane;
    @FXML
    private MenuButton moreFunctionsButton;
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
    private final String hexPrefix = "0x";
    private final String binPrefix = "Bx";
    private final String octPrefix = "ox";
    @Override
    public String getCalculatorName() {
        return "Programador";
    }

    private final OperacionesBitABit logica = new OperacionesBitABit();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonsOut(castToUnsignedByte("0",10));
        inputType.selectedToggleProperty().addListener(changeInputType());
        inputType.selectToggle(DECButton);
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
                if (!DECButton.isSelected()){
                    modifyMemoryString("-A", memory[1].substring(2));
                }else{
                    modifyMemoryString("-A", memory[1]);
                }
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
    private ChangeListener<Toggle> changeInputType(){
        return (observableValue, oldToggle, newToggle) ->
        {
            ToggleButton newToggleButton = (ToggleButton) newToggle;
            ToggleButton oldToggleButton = (ToggleButton) oldToggle;
            if (oldToggle!=null) {
                int actualBase = 10;
                if (oldToggleButton.equals(HEXButton)) {
                    actualBase = 16;
                }
                if (oldToggleButton.equals(OCTButton)) {
                    actualBase = 8;
                }
                if (oldToggleButton.equals(BINButton)) {
                    actualBase = 2;
                }
                for (int i = 0; i < memory.length; i++) {
                    int toChange =0;
                    if (memory[i].contains("x")){
                        toChange = castToUnsignedByte(memory[i].substring(2), actualBase);
                    } else if (!memory[i].equals("")) {
                        toChange = castToUnsignedByte(memory[i],actualBase);
                    }
                    if (newToggleButton.equals(DECButton)) {
                        memory[i] = "%d".formatted(toChange);
                    }
                    if (newToggleButton.equals(HEXButton)) {
                        memory[i] = (hexPrefix + "%X").formatted(toChange);
                    }
                    if (newToggleButton.equals(OCTButton)) {
                        memory[i] = (octPrefix + "%o").formatted(toChange);
                    }
                    if (newToggleButton.equals(BINButton)) {
                        memory[i] = (binPrefix + "%s").formatted(Integer.toUnsignedString(toChange,2));
                    }
                }
                switch (stateProperty.get()){
                    case FIRST_INPUT -> {
                        if (memory[0].length() >= 3) renewActual(memory[0].substring(2));
                    }
                    case SECOND_INPUT -> {
                        if ( memory[1].length() >=3 )renewActual(memory[1].substring(2));
                    }
                    case ACTION_PERFORMED -> doClear();
                }
            }
            if (newToggleButton.equals(OCTButton)) {
                    for (Node keysButtons : keyboardGridPane.getChildren()) {
                        if (keysButtons instanceof ButtonBase) {
                            try {
                                ButtonCodes buttonId = ButtonCodes.valueOf(keysButtons.getId());
                                switch (buttonId) {
                                    case eight, nine -> keysButtons.setDisable(true);
                                    case one, two, three, four, five, six, seven -> keysButtons.setDisable(false);
                                }
                            }catch (IllegalArgumentException ignored){}
                        }
                    }
                }
            if (newToggleButton.equals(BINButton)) {
                    for (Node keysButtons : keyboardGridPane.getChildren()) {
                        if (keysButtons instanceof ButtonBase) {
                            try {
                                ButtonCodes buttonId = ButtonCodes.valueOf(keysButtons.getId());
                                switch (buttonId) {
                                    case two, three, four, five, six, seven, eight, nine ,a, b, c, d, e, f -> keysButtons.setDisable(true);
                                }
                            }catch (IllegalArgumentException ignored){}
                        }
                    }
                }
            if (newToggleButton.equals(HEXButton)) {
                    for (Node keysButtons : keyboardGridPane.getChildren()) {
                        if (keysButtons instanceof ButtonBase) {
                            try {
                                ButtonCodes buttonId = ButtonCodes.valueOf(keysButtons.getId());
                                switch (buttonId) {
                                    case one, two, three, four, five, six, seven, eight, nine, a, b, c, d, e, f ->
                                            keysButtons.setDisable(false);
                                }
                            }catch (IllegalArgumentException ignored){}
                        }
                    }
                }
            if (newToggleButton.equals(DECButton)) {
                    for (Node keysButtons : keyboardGridPane.getChildren()) {
                        if (keysButtons instanceof ButtonBase) {
                            try {
                                ButtonCodes buttonId = ButtonCodes.valueOf(keysButtons.getId());
                                switch (buttonId) {
                                    case one, two, three, four, five, six, seven, eight, nine -> keysButtons.setDisable(false);
                                    case a, b, c, d, e, f -> keysButtons.setDisable(true);
                                }
                            }catch (IllegalArgumentException ignored){}
                        }
                    }
                }

        };
    }
    private void onActionChosen(String actionSymbol){
        onActionChosen(actionSymbol,false);
    }
    private void onActionChosen(String actionSymbol, boolean oneArg) {
        String input;
        if (oneArg){
            if (memory[0].contains("x")){
                input = memory[0].substring(2);
            }else {
                input = memory[0];
            }
            modifyMemoryString("-A", actionSymbol.formatted(input));
            stateProperty.set(CalculationState.ONE_ARG_ACTION_CHOOSE);
        }else {
            if (memory[0].contains("x")){
                input = memory[0].substring(2);
            }else {
                input = memory[0];
            }
            modifyMemoryString("-A", input + actionSymbol);
            stateProperty.set(CalculationState.ACTION_CHOOSE);
        }
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
            memoryStringBuilder.append(args);
        }
        if (flag.equals("-CL")){
            memoryStringBuilder.deleteCharAt(memoryStringBuilder.length()-1);
            memoryLabel.setText(memoryStringBuilder.toString());
        }
    }

    private String checkInput(String inputString) throws NumberFormatException, InputMismatchException {
        return checkInput(inputString, null);
    }

    private String checkInput(String inputString, String plusOther) throws NumberFormatException, InputMismatchException {
        if (plusOther != null) {
            int base = 10;
            int index = 0;
            if (HEXButton.isSelected()) {base = 16; index=2;}
            if (OCTButton.isSelected()) {base=8; index=2;}
            if (BINButton.isSelected()) {base=2; index=2;}
            if (Integer.parseUnsignedInt(inputString.substring(index) + plusOther,base) > 255){
                throw new InputMismatchException("El valor de entrada sobrepasa un byte");
            }
            return plusOther;
        }
        if (!inputType.getSelectedToggle().equals(DECButton) && inputString.matches("\\d++")){
            throw new NumberFormatException("La entrada es Dec");
        }
        if(!inputType.getSelectedToggle().equals(BINButton) && inputString.matches("Bx[0-1]++")){
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
        if (DECButton.isSelected()) {
            setButtonsOut(castToUnsignedByte(any, 10));
            actualStringBuilder.replace(0,actualStringBuilder.length(),any);
        }
        if (any.equals("0")){
            setButtonsOut(0);
        }
        if (any.contains("x")) {
            String substring = any.substring(2);
            if (HEXButton.isSelected()) {
                if (any.length() > 4){
                    substring = any.substring(any.length() - 2);
                }
                setButtonsOut(castToUnsignedByte(substring, 16));

            }
            if (OCTButton.isSelected()) {
                if (any.length() > 5){
                    substring = any.substring(any.length()-3);
                }
                setButtonsOut(castToUnsignedByte(substring, 8));
            }
            if (BINButton.isSelected()) {
                if (any.length() > 10){
                    substring = any.substring(any.length()-8);
                }
                setButtonsOut(castToUnsignedByte(substring, 2));
            }
            actualStringBuilder.replace(0,actualStringBuilder.length(), substring);
        }else{
            if (inputType.getSelectedToggle().equals(HEXButton)) {
                setButtonsOut(castToUnsignedByte(any, 16));
                actualStringBuilder.replace(0,actualStringBuilder.length(), any);
            }
            if (inputType.getSelectedToggle().equals(OCTButton)) {
                setButtonsOut(castToUnsignedByte(any, 8));
                actualStringBuilder.replace(0,actualStringBuilder.length(), any);
            }
            if (inputType.getSelectedToggle().equals(BINButton)) {
                setButtonsOut(castToUnsignedByte(any, 2));
                actualStringBuilder.replace(0,actualStringBuilder.length(), any);
            }
        }
        actualLabel.setText(actualStringBuilder.toString());
    }
    private void updateActual(String any){
        if (any.equals("-D")){
            actualStringBuilder.deleteCharAt(actualStringBuilder.length()-1);
        }else {
            actualStringBuilder.append(any);

        }
        actualLabel.setText(actualStringBuilder.toString());
        if (inputType.getSelectedToggle().equals(DECButton)){
            setButtonsOut(castToUnsignedByte(actualStringBuilder.toString(),10));
        }
        if (inputType.getSelectedToggle().equals(HEXButton)){
            setButtonsOut(castToUnsignedByte(actualStringBuilder.toString(),16));
        }
        if (inputType.getSelectedToggle().equals(OCTButton)){
            setButtonsOut(castToUnsignedByte(actualStringBuilder.toString(),8));
        }
        if (inputType.getSelectedToggle().equals(BINButton)){
            setButtonsOut(castToUnsignedByte(actualStringBuilder.toString(),2));
        }
    }
    private void doInput(String inputNumber) {
        int calculationStateOrdinal = stateProperty.get().ordinal() <= 1 ? stateProperty.get().ordinal() : 0;
        String validInput = "";
        if (stateProperty.get().ordinal() != CalculationState.ACTION_PERFORMED.ordinal()) {
            try {
                validInput = checkInput(!memory[calculationStateOrdinal].equals("") ? memory[calculationStateOrdinal] : "0", inputNumber);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! : " + e.getMessage());
            } catch (InputMismatchException upperLimit) {
                return;
            }
        }else validInput = inputNumber;
        if ((!memory[calculationStateOrdinal].equals("") && stateProperty.get().ordinal() == CalculationState.ACTION_PERFORMED.ordinal())){
            ProgramadorController.this.stateProperty.set(CalculationState.FIRST_INPUT);
            renewActual(validInput);
        }else if ( memory[calculationStateOrdinal].equals("") || memory[calculationStateOrdinal].equals("0")){
            renewActual(validInput);
        } else if (
                stateProperty.get().ordinal() == CalculationState.SECOND_INPUT.ordinal()
                && (memory[calculationStateOrdinal].equals(hexPrefix + "0") || memory[calculationStateOrdinal].equals(octPrefix + "0") || memory[calculationStateOrdinal].equals(binPrefix + "0"))
        ){
            renewActual(validInput);
        }else {
            updateActual(validInput);
        }

        if (memory[calculationStateOrdinal].equals("")){
            memory[calculationStateOrdinal] = validInput;
        }else {
            memory[calculationStateOrdinal] = memory[calculationStateOrdinal] + validInput;
        }

    }
    private double getInput(int inputIndex){
        if(inputType.getSelectedToggle().equals(DECButton)){
            return castToUnsignedByte(memory[inputIndex], 10);
        }
        if(inputType.getSelectedToggle().equals(HEXButton)){
            return castToUnsignedByte(memory[inputIndex].substring(2),16);
        }
        if(inputType.getSelectedToggle().equals(OCTButton)){
            return castToUnsignedByte(memory[inputIndex].substring(2),8);
        }
        if (inputType.getSelectedToggle().equals(BINButton)){
            return castToUnsignedByte(memory[inputIndex].substring(2),2);
        }
        return 0;
    }
    private void doClear() {
        modifyMemoryString("-C");
        if (HEXButton.isSelected()){
            memory[0] = hexPrefix + "0";
            memory[1] = hexPrefix + "0";
            memory[2] = hexPrefix + "0";
        }
        if (OCTButton.isSelected()){
            memory[0] = octPrefix + "0";
            memory[1] = octPrefix + "0";
            memory[2] = octPrefix + "0";
        }
        if (BINButton.isSelected()){
            memory[0] = binPrefix + "0";
            memory[1] = binPrefix + "0";
            memory[2] = binPrefix + "0";
        }
        if (DECButton.isSelected()){
            memory[0] = "0";
            memory[1] = "0";
            memory[2] = "0";
        }
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
        ButtonCodes buttonCode = null;
        if (actionEvent.getSource() instanceof ButtonBase){
            buttonCode = ButtonCodes.valueOf(((ButtonBase) actionEvent.getSource()).getId());
        }
        if (actionEvent.getSource() instanceof MenuItem){
            buttonCode = ButtonCodes.valueOf(((MenuItem) actionEvent.getSource()).getId());
        }
        switch(Objects.requireNonNull(buttonCode)){
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
            case mod -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::modulo;
            }

            case a -> doInput("A");
            case b -> doInput("B");
            case c -> doInput("C");
            case d -> doInput("D");
            case e -> doInput("E");
            case f -> doInput("F");

            case equal -> doCalculation();

            case clear -> doClear();
            case delete -> undoInput();
            case and -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::AND;
            }
            case or -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::OR;
            }
            case not -> {
                onActionChosen(buttonCode.getSymbol(),true);
                actionChosen = ((aDouble, aDouble2) -> logica.NOT(aDouble));
            }
            case nand -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::NAND;
            }
            case nor -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::NOR;
            }
            case xor -> {
                onActionChosen(buttonCode.getSymbol());
                actionChosen = logica::XOR;
            }

        }
    }
    private int castToUnsignedByte(String numberAsString, int base){
        if (numberAsString.equals("")) return 0;
        return Integer.parseInt(numberAsString, base) & 0xFF;
    }
    private void setButtonsOut(int newNumber){
        HEXButton.setText("%02X".formatted(newNumber));
        DECButton.setText("%03d".formatted(newNumber));
        OCTButton.setText("%03o".formatted(newNumber));
        BINButton.setText("%8s".formatted(Integer.toBinaryString(newNumber + 0x100)).substring(1));
    }
    private void doCalculation(){
        double firstArg = getInput(0);
        double secondArg = getInput(1);

        double calcutation = actionChosen.apply(firstArg,secondArg);
        if (DECButton.isSelected()) {
            memory[2] = "%d".formatted((int)calcutation);
        }
        if (HEXButton.isSelected()) {
            memory[2] = (hexPrefix + "%X").formatted((int)calcutation);
        }
        if (OCTButton.isSelected()) {
            memory[2] = (octPrefix + "%o").formatted((int)calcutation);
        }
        if (BINButton.isSelected()) {
            memory[2] = (binPrefix + "%s").formatted(Integer.toUnsignedString((int)calcutation,2));
        }

        stateProperty.set(CalculationState.ACTION_PERFORMED);
    }
}
