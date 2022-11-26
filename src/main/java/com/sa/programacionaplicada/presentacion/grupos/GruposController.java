package com.sa.programacionaplicada.presentacion.grupos;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.Grupos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GruposController implements Initializable{

        @FXML
        private TableView<Grupos> table;
        @FXML
        private TableColumn<Grupos,Long> id;
        @FXML
        private TableColumn<Grupos,Integer> asignatura;
        @FXML
        private TableColumn<Grupos, String> salon_Asignado;
        @FXML
        private TableColumn<Grupos,String> docente_Asignado;
        @FXML
        private TableColumn<Grupos,Integer> cantidadInscritos;
        @FXML
        private TableColumn<Grupos, String> horario;

        private final ObservableList<Grupos> conductoress = FXCollections.observableArrayList();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            conductoress.add(new Grupos(1L));
            setupColumns();
            conductoress.remove(0);
            conductoress.addAll(Objects.requireNonNull(Grupos.consultarTodos()));
            table.setItems(conductoress);

        }
        public void searchAll(){
            conductoress.clear();
            conductoress.addAll(Objects.requireNonNull(Grupos.consultarTodos()));
        }
        private void setupColumns(){
            id.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).idProperty().getName()));
            asignatura.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).asignaturaProperty().getName()));
            salon_Asignado.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).salonAsignadoProperty().getName()));
            docente_Asignado.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).docenteAsignadoProperty().getName()));
            cantidadInscritos.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).cantidadInscritosProperty().getName()));
            horario.setCellValueFactory(new PropertyValueFactory<>(conductoress.get(0).horarioProperty().getName()));
        }
}
