package com.sa.programacionaplicada.funcionesGUI;/*Author:sfeli*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;

public class FuncionesViewController implements Initializable {
    @FXML
    private ComboBox<String> selectionComboBox;
    @FXML
    private TextField inputTextField;
    @FXML
    private Button calcularButton;
    @FXML
    private Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setup the prompt text
        selectionComboBox.setPromptText("Selecciona una Función ");
        //Coloca que la comboBox no es editable
        selectionComboBox.setEditable(false);
        //Coloca los items de la comboBox
        selectionComboBox.getItems().addAll(List.of("Seno", "Coseno","Tangente","Potencia","Fibonacci","Raíz Cuadrada","Factorial"));
        //Setup the cell factory
        selectionComboBox.setCellFactory(container -> new ListCell<>(){
            {
                setContentDisplay(ContentDisplay.CENTER);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty){
                    setText(null);
                    setGraphic(null);
                }else {
                    setText(item);
                }
            }
        });
        //Cambia el prompt text del campo de entrada cuando se selecciona cierta función
        selectionComboBox.valueProperty().addListener((observableValue, s, t1) -> {
            if(t1.equals("Seno") || t1.equals("Coseno") || t1.equals("Tangente")){
                inputTextField.setPromptText("Escribe un ángulo en grados");
            } else if (t1.equals("Fibonacci")) {
                inputTextField.setPromptText("Escribe cual termino deseas");
            } else if (t1.equals("Potencia")) {
                inputTextField.setPromptText("Escribe un potencia así : a^b");
            }else inputTextField.setPromptText("Escribe un número");
        });
        //Coloca la función a ejecutar cuando se dispare el botón
        calcularButton.setOnAction(this::calculate);
    }

    private void calculate(ActionEvent actionEvent){
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(3);
        double input;
        double input2;
        String selectedFunction = selectionComboBox.getValue();
        try {
            if (selectedFunction.equals("Potencia")){
                String a = inputTextField.getText().substring(0,inputTextField.getText().indexOf("^"));
                String b = inputTextField.getText().substring(inputTextField.getText().indexOf("^")+1);
                input = Double.parseDouble(a);
                input2 = Double.parseDouble(b);
            }else {
                input = Double.parseDouble(inputTextField.getText());
                input2 =0;
            }
            Double result = switch (selectedFunction) {
                case "Seno" -> Funciones.calcularseno(input);
                case "Coseno" -> Funciones.calcularcoseno(input);
                case "Tangente" -> Funciones.calculartangente(input);
                case "Potencia" -> Funciones.calcularpotencia(input,input2);
                case "Fibonacci" -> (double) Funciones.fibonacci((int) input);
                case "Raíz Cuadrada" -> Funciones.calcularraiz(input);
                case "Factorial" -> input > 400.0 ? Double.NaN : Funciones.factorial((int) input);
                default -> 0d;
            };
            resultLabel.setText("El resultado es: " + numberFormat.format(result));
        }catch (Exception e){
            e.printStackTrace();
            resultLabel.setText("Parametro de entrada invalido. Ingresaste un numero?");
        }
    }
}
