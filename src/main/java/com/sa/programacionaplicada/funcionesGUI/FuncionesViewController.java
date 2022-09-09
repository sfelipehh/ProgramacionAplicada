package com.sa.programacionaplicada.funcionesGUI;/*Author:sfeli*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
        selectionComboBox.getItems().addAll(List.of("Seno", "Cos"));
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

        //Coloca la función a ejecutar cuando se dispare el botón
        calcularButton.setOnAction(this::calculate);
    }

    private void calculate(ActionEvent actionEvent){
        double input;
        String selectedFunction = selectionComboBox.getValue();
        try {
            input = Double.parseDouble(inputTextField.getText());
            //String result = Funciones.Any(input)
            resultLabel.setText("El resultado es: ");
        }catch (Exception e){
            resultLabel.setText("Parametro de entrada invalido. Ingresaste un numero?");
        }
    }
}
