package com.sa.programacionaplicada.procesamientoDatos.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.HomeController;
import com.sa.programacionaplicada.procesamientoDatos.logica.DistribucionUnidimensional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DistribucionEmpresaController implements Initializable {

    @FXML
    private Button doDistributionButton;
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
    private final TextInputDialog setupDialog = new TextInputDialog();
    private EventHandler<ActionEvent> setupDialogEvent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //anyInputCommitButton.setOnAction(this::getQuantityOfDivisions);
        //anyInput.setOnAction(this::getQuantityOfDivisions);
        doDistributionButton.setOnAction(ev ->{
            setupDialog.setHeaderText("Distribución de Operarios");
            setupDialog.setContentText("Ingrese la cantidad de Operarios disponibles");
            setupDialog.getEditor().setPromptText("Cantidad de Operarios");
            setupDialogEvent = this::doDistribution;
            setupDialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION,setupDialogEvent);
            setupDialog.show();
        });
        doDistributionButton.setVisible(false);
        quitButton.setOnAction(this::goBack);
        quitButton.setVisible(false);

        setupDialog.initModality(Modality.APPLICATION_MODAL);
        setupDialog.setTitle("Configuración");
        setupDialog.setHeaderText("Configuración de la tabla");
        setupDialog.setContentText("Ingrese la cantidad de Divisiones de la Empresa");
        setupDialog.getEditor().setPromptText("Cantidad de Divisiones");
        setupDialogEvent = this::getQuantityOfDivisions;
        setupDialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION,setupDialogEvent);
        setupDialog.show();

    }

    private void getQuantityOfDivisions(ActionEvent actionEvent){
        int input;
        try{
            input = Integer.parseInt(setupDialog.getEditor().getText());
            for (int i = 0; i < input; i++) {
                divisionesList.add(i, new DivisionEmpresa(i));
            }
            setupColumns();
            presentationTable.setItems(divisionesList);
            presentationTable.setDisable(false);
            doDistributionButton.setVisible(true);
            quitButton.setVisible(true);
            setupDialog.getDialogPane().lookupButton(ButtonType.OK).removeEventFilter(ActionEvent.ACTION,setupDialogEvent);
            setupDialog.getEditor().setText(null);
            setupDialog.close();
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            setupDialog.getEditor().setText(null);
            Alert badInput = new Alert(Alert.AlertType.ERROR, "No ingresaste una cantidad de Divisiones correcta, intentalo de nuevo");
            badInput.showAndWait();
            setupDialog.getEditor().setPromptText("Número no ingresado");
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
        fillPromEquiposLabel(logica.promedio(devicesPerDivision));
        fillMaxEquiposDivisionLabel(divisionesList.get(logica.maximo(devicesPerDivision)));
        fillMinEquiposDivisionLabel(divisionesList.get(logica.minimo(devicesPerDivision)));
    }

    private void doDistribution(ActionEvent actionEvent){
        int cantidadOperarios;
        try{
            cantidadOperarios = Integer.parseInt(setupDialog.getEditor().getText());
            int[] devicesPerDivision = divisionesList.stream().mapToInt(DivisionEmpresa::getDevices).toArray();
            int[] distribucion = logica.distribucion(cantidadOperarios, devicesPerDivision);
            for (int i = 0; i < distribucion.length; i++) {
                divisionesList.get(i).setOperators(distribucion[i]);
            }
            fillMaxEquiposDivisionLabel(divisionesList.get(logica.maximo(devicesPerDivision)));
            fillMinEquiposDivisionLabel(divisionesList.get(logica.minimo(devicesPerDivision)));

            setupDialog.getDialogPane().lookupButton(ButtonType.OK).removeEventFilter(ActionEvent.ACTION,setupDialogEvent);
            setupDialog.getEditor().setText(null);
            setupDialog.close();
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            setupDialog.getEditor().setText(null);
            Alert badInput = new Alert(Alert.AlertType.ERROR, "No ingresaste una cantidad de Operarios correcta, intentalo de nuevo");
            badInput.showAndWait();
            setupDialog.getEditor().setPromptText("Número no ingresado");
        }

    }
    private void fillTotalEquiposLabel(int totalDevices){
        String totalEquiposFormat = "Total Equipos:\n%d";
        totalDevicesLabel.setText(totalEquiposFormat.formatted(totalDevices));
    }
    private void fillPromEquiposLabel(int averageDevices){
        String promEquiposFormat = "Promedio Equipos División:\n%d";
        averageDevicesLabel.setText(promEquiposFormat.formatted(averageDevices));
    }
    private void fillMaxEquiposDivisionLabel(DivisionEmpresa divisionMaxDevices){
        String maxEquiposDivisionFormat = "División con más Equipos:\n%s";
        maxDevicesDivisionLabel.setText(maxEquiposDivisionFormat.formatted(divisionMaxDevices.toString()));
    }
    private void fillMinEquiposDivisionLabel(DivisionEmpresa divisionMinDevices){
        String minEquiposDivisionFormat = "División con menos Equipos:\n%s";
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
    private void goBack(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("home-view.fxml"));
        Stage window = (Stage) quitButton.getScene().getWindow();
        try {
            window.setScene(new Scene(loader.load(), 600, 400));
        }catch (IOException e){
            e.printStackTrace();
        }
        window.show();
    }
}
