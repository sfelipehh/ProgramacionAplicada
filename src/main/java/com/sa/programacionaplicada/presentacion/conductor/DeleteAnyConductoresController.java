package com.sa.programacionaplicada.presentacion.conductor;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.Conductores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class DeleteAnyConductoresController {
    @FXML
    private TextField idField;

    private final Conductores object = new Conductores();
    public void doDelete(ActionEvent actionEvent) {
        Alert alert;
        if(object.EliminarConductor(Long.parseLong(idField.getText()))){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Conductor Eliminado");
            alert.setHeaderText("El Conductor " + idField.getText() + " ha sido eliminado");
        }else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Conductor NO Eliminado");
            alert.setHeaderText("El Conductor " + idField.getText() + " no logro ser eliminado");
        }
        alert.show();
        idField.setText(null);
    }
}
