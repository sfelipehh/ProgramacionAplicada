package com.sa.programacionaplicada.presentacion.grupos;/*Author:sfeli*/

import com.jfoenix.controls.JFXButton;
import com.sa.programacionaplicada.logica.Asignaturas;
import com.sa.programacionaplicada.logica.Grupos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ModifyAnyGrupoController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField salonField;
    @FXML
    private TextField docenteField;
    @FXML
    private ChoiceBox<Asignaturas> idAsignaturaChoiceBox;
    @FXML
    private JFXButton modificationButton;
    @FXML
    private TextField candidadInscritosField;
    private Grupos grupo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Asignaturas.consultarTodos();
        for (Asignaturas asignatura:
                Asignaturas.getAsignaturas_id_names()) {
            idAsignaturaChoiceBox.getItems().add(asignatura);
        }
        grupo = new Grupos();
    }
    @FXML
    private void doSearch(ActionEvent actionEvent){
        if (grupo.consultarPorCodigo(Long.valueOf(idField.getText()))){
            fillFields();
            modificationButton.setDisable(false);
        }

    }
    private void doModification(ActionEvent actionEvent){

    }

    private void fillFields(){
        salonField.setText(grupo.getSalonAsignado());
        salonField.setDisable(false);
        docenteField.setText(grupo.getDocenteAsignado());
        docenteField.setDisable(false);
        idAsignaturaChoiceBox.setValue(Asignaturas.buscarPorId(grupo.getAsignatura()));
        idAsignaturaChoiceBox.setDisable(false);
        candidadInscritosField.setText(String.valueOf(grupo.getCantidadInscritos()));
        candidadInscritosField.setDisable(false);
    }
}
