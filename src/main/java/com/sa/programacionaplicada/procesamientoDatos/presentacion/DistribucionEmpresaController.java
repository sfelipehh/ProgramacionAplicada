package com.sa.programacionaplicada.procesamientoDatos.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.procesamientoDatos.logica.DistribucionUnidimensional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class DistribucionEmpresaController implements Initializable {

    @FXML
    private Label anyInputLabel;

    @FXML
    private TextField anyInput;

    @FXML
    private Button anyInputCommitButton;

    @FXML
    private Button quitButton;

    @FXML
    private TableView<DivisionEmpresa> presentationTable;

    @FXML
    private TableColumn<DivisionEmpresa,Integer> divisionIdColumn;

    @FXML
    private TableColumn<DivisionEmpresa, String> nameDivisionColumn;

    @FXML
    private TableColumn<DivisionEmpresa, Integer> devicesDivisionColumn;

    @FXML
    private TableColumn<DivisionEmpresa, Integer> operatorsDivisionColumn;
    @FXML
    private Label totalDevicesLabel;
    @FXML
    private Label averageDevicesLabel;

    @FXML
    private Label maxDevicesDivisionLabel;

    @FXML
    private Label minDevicesDivisionLabel;

    private final DistribucionUnidimensional logica = new DistribucionUnidimensional();
    private final ObservableList<DivisionEmpresa> divisionesList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presentationTable.setItems(divisionesList);
        anyInputCommitButton.setOnAction(this::getQuantityOfDivisions);
        anyInput.setOnAction(this::getQuantityOfDivisions);
        quitButton.setOnAction(ev -> Platform.exit());
    }

    private void getQuantityOfDivisions(ActionEvent actionEvent){
        int input;
        try{
            input = Integer.parseInt(anyInput.getText());
            for (int i = 0; i < input; i++) {
                divisionesList.add(i, new DivisionEmpresa(i));
            }
            setupColumns();
            presentationTable.setDisable(false);
            anyInputLabel.setText("Cantidad de operarios");
            anyInput.setPromptText("Cantidad operarios");
            anyInput.setText(null);
            anyInput.setOnAction(this::doDistribution);
            anyInputCommitButton.setText("Realizar distribución");
            anyInputCommitButton.setOnAction(this::doDistribution);
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            anyInput.setText(null);
            Alert badInput = new Alert(Alert.AlertType.ERROR, "No ingresaste una cantidad de Divisiones correcta, intentalo de nuevo");
            badInput.showAndWait();
            anyInput.setPromptText("Número no ingresado");
        }
    }

    private <T> void processData(TableColumn.CellEditEvent<DivisionEmpresa,T> editEvent){
        TableColumn<DivisionEmpresa, T> tableColumn = editEvent.getTableColumn();
        if (tableColumn.equals(nameDivisionColumn)){
            System.out.println("Se cambio el nombre de alguna división");
            editEvent.getRowValue().setName((String) editEvent.getNewValue());
        }
        if (tableColumn.equals(devicesDivisionColumn)){
            System.out.println("Se cambio la cantidad de equipos de alguna división");
            editEvent.getRowValue().setDevices((int) editEvent.getNewValue());
        }
        fillTotalEquiposLabel(divisionesList.stream().mapToInt(DivisionEmpresa::getDevices).sum());
        int[] devicesPerDivision = divisionesList.stream().mapToInt(DivisionEmpresa::getDevices).toArray();
        //fillPromEquiposLabel(logica.promedio(devicesPerDivision))
        //fillMaxEquiposDivisionLabel(divisionesList.get(logica.maxEquipos(devicesPerDivision)))
        //fillMinEquiposDivisionLabel(divisionesList.get(logica.maxEquipos(devicesPerDivision)))
    }

    private void doDistribution(ActionEvent actionEvent){
        int input;
        try{
            input = Integer.parseInt(anyInput.getText());
            //logica.distribucion(input);
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            anyInput.setText(null);
            Alert badInput = new Alert(Alert.AlertType.ERROR, "No ingresaste una cantidad de Operarios correcta, intentalo de nuevo");
            badInput.showAndWait();
            anyInput.setPromptText("Número no ingresado");
        }

    }
    private void fillTotalEquiposLabel(int totalDevices){
        String totalEquiposFormat = "Total Equipos: %d";
        totalDevicesLabel.setText(totalEquiposFormat.formatted(totalDevices));
    }

    private void fillPromEquiposLabel(int averageDevices){
        String promEquiposFormat = "Promedio Equipos División: %d";
        averageDevicesLabel.setText(promEquiposFormat.formatted(averageDevices));
    }

    private void fillMaxEquiposDivisionLabel(DivisionEmpresa divisionMaxDevices){
        String maxEquiposDivisionFormat = "División con más Equipos: %s";
        maxDevicesDivisionLabel.setText(maxEquiposDivisionFormat.formatted(divisionMaxDevices.toString()));
    }

    private void fillMinEquiposDivisionLabel(DivisionEmpresa divisionMinDevices){
        String minEquiposDivisionFormat = "División con menos Equipos: %s";
        minDevicesDivisionLabel.setText(minEquiposDivisionFormat.formatted(divisionMinDevices.toString()));
    }
    private void setupColumns(){
        divisionIdColumn.setCellValueFactory(
                new PropertyValueFactory<>(divisionesList.get(0).idProperty().getName())
        );

        nameDivisionColumn.setCellValueFactory(
                new PropertyValueFactory<>(divisionesList.get(0).nameProperty().getName())
        );
        nameDivisionColumn.setCellFactory(column -> new TextFieldTableCell<>(new DefaultStringConverter()));
        nameDivisionColumn.setOnEditCommit(this::processData);

        devicesDivisionColumn.setCellValueFactory(
                new PropertyValueFactory<>(divisionesList.get(0).devicesProperty().getName())
        );
        devicesDivisionColumn.setCellFactory(column -> new TextFieldTableCell<>(new IntegerStringConverter()));
        devicesDivisionColumn.setOnEditCommit(this::processData);

        operatorsDivisionColumn.setCellValueFactory(
                new PropertyValueFactory<>(divisionesList.get(0).operatorsProperty().getName())
        );
    }
}
