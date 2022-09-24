package com.sa.programacionaplicada.procesamientoDatos.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.HomeController;
import com.sa.programacionaplicada.procesamientoDatos.logica.DistribucionBidimensional;
import javafx.collections.FXCollections;
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
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DistribucionEdificioController implements Initializable {
    @FXML
    private Button addMoneyButton;
    @FXML
    private Button searchDevicesButton;
    @FXML
    private Button quitButton;
    @FXML
    private TableView<PisoEdificio> presentationTable;
    @FXML
    private TableColumn<PisoEdificio, String> floorIdColumn;
    private final ArrayList<TableColumn<PisoEdificio,Number>> officesColumns = new ArrayList<>();
    @FXML
    private TableColumn<PisoEdificio, Integer> totalFloorColumn;
    @FXML
    private TableColumn<PisoEdificio, String> percentageColumn;
    @FXML
    private TableColumn<PisoEdificio, Integer> budgetColumn;
    private final ObservableList<PisoEdificio> floorsList = FXCollections.observableArrayList();
    private final TextInputDialog setupDialog = new TextInputDialog();
    private EventHandler<ActionEvent> setupDialogEvent;
    private final DistribucionBidimensional logica = new DistribucionBidimensional();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMoneyButton.setOnAction(actionEvent -> {
            setupDialog.setHeaderText("Asignación de Presupuesto");
            setupDialog.setContentText("Ingrese el Presupuesto: ");
            setupDialog.getEditor().setPromptText("Presupuesto");
            setupDialog.getEditor().setText(null);
            setupDialogEvent = this::addMoney;
            setupDialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, setupDialogEvent);
            setupDialog.show();
        });
        addMoneyButton.setVisible(false);
        searchDevicesButton.setOnAction(actionEvent -> {
            setupDialog.setHeaderText("Buscar cantidad de Equipos:");
            setupDialog.setContentText("Ingrese la cantidad de Equipos que desea buscar:");
            setupDialog.getEditor().setPromptText("Cantidad de Equipos");
            setupDialog.getEditor().setText(null);
            setupDialogEvent = this::searchDevices;
            setupDialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, setupDialogEvent);
            setupDialog.show();
        });
        searchDevicesButton.setVisible(false);
        quitButton.setOnAction(this::goBack);
        quitButton.setVisible(false);

        setupDialog.initModality(Modality.APPLICATION_MODAL);
        setupDialog.setTitle("Configuración");
        setupDialog.setHeaderText("Configuración de la tabla:");
        setupDialog.setContentText("Ingrese la cantidad de Pisos del edificio");
        setupDialog.getEditor().setPromptText("Cantidad de Pisos");
        setupDialogEvent = this::getQuantityOfFloorsAndOffices;
        setupDialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION,setupDialogEvent);
        setupDialog.show();
    }
    private void getQuantityOfFloorsAndOffices(ActionEvent actionEvent){
        actionEvent.consume();
        int input;
        try{
            input = Integer.parseInt(setupDialog.getEditor().getText());
            if (floorsList.isEmpty()){
                for (int i = 0; i < input; i++) {
                    floorsList.add(i, new PisoEdificio(i));
                }
                setupDialog.setContentText("Ingrese la cantidad de Oficinas por Piso");
                setupDialog.getEditor().setPromptText("Cantidad de Oficinas");
                setupDialog.getEditor().setText(null);
            }else {
                for (PisoEdificio floor:
                     floorsList) {
                    floor.setOffices(input);
                }
                setupColumns();
                presentationTable.setItems(floorsList);
                presentationTable.setDisable(false);
                addMoneyButton.setVisible(true);
                searchDevicesButton.setVisible(true);
                quitButton.setVisible(true);
                setupDialog.getDialogPane().lookupButton(ButtonType.OK).removeEventFilter(ActionEvent.ACTION,setupDialogEvent);
                setupDialog.getEditor().setText(null);
                setupDialog.close();
            }
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            Alert badInput = new Alert(Alert.AlertType.ERROR, "No ingresaste una cantidad de Divisiones correcta, intentalo de nuevo");
            badInput.showAndWait();

        }
    }
    private void setupColumns(){
        floorIdColumn.setCellValueFactory(new PropertyValueFactory<>(floorsList.get(0).idProperty().getName()));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<>(floorsList.get(0).percentageProperty().getName()));
        totalFloorColumn.setCellValueFactory(new PropertyValueFactory<>(floorsList.get(0).totalProperty().getName()));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>(floorsList.get(0).budgetProperty().getName()));
        for (int i = 0; i < floorsList.get(0).sizeOffices(); i++) {
            TableColumn<PisoEdificio,Number> tableColumn = new TableColumn<>("Oficina " + i);
            int finalI = i;
            tableColumn.setCellValueFactory(
                    item -> item.getValue().officeDevicesProperty(finalI)
            );
            tableColumn.setCellFactory(column -> new TextFieldTableCell<>(new NumberStringConverter()));
            tableColumn.setOnEditCommit(this::processData);
            officesColumns.add(i, tableColumn);
            presentationTable.getColumns().add(i+1 , tableColumn);
        }
    }
    private void processData(TableColumn.CellEditEvent<PisoEdificio,Number> editEvent){
        int officeIndex = officesColumns.indexOf(editEvent.getTableColumn());
        System.out.println("Se cambio la cantidad de Equipos en el piso: " + editEvent.getTablePosition().getRow()
                            + " Oficina:" + officeIndex);
        editEvent.getRowValue().setOfficeDevices(editEvent.getNewValue(),officeIndex);
        System.out.println(floorsList);
        int[][] devicesInOffices = getArray();
        //Aplicación de Lógica !

        int[] totalsPerFloor = logica.totales(devicesInOffices);
        for (int i=0; i < totalsPerFloor.length; i++) {
            floorsList.get(i).setTotal(totalsPerFloor[i]);
        }

        double[] percentagePerFloor = logica.porcentajes(devicesInOffices);
        for (int i=0; i < totalsPerFloor.length; i++) {
            floorsList.get(i).setPercentage(percentagePerFloor[i]);
        }
    }
    private int[][] getArray() {
        int quantityOfFloors = floorsList.size();
        int quantityOfOfficesPerFloor = floorsList.get(0).sizeOffices();
        int[][] devicesInOffices = new int[quantityOfFloors][quantityOfOfficesPerFloor];
        for (int i = 0; i < devicesInOffices.length; i++) {
            int[] tempOfficess = new int[quantityOfOfficesPerFloor];
            for (int j = 0; j < tempOfficess.length; j++) {
                tempOfficess[j] = floorsList.get(i).getOfficeDevices(j);
            }
            devicesInOffices[i] = tempOfficess;
        }
        return devicesInOffices;
    }
    private void addMoney(ActionEvent actionEvent){
        actionEvent.consume();
        int budget;
        try {
            budget = Integer.parseInt(setupDialog.getEditor().getText());
            int [] budgetPerFloor = logica.presupuesto(budget,getArray());
            for (int i = 0; i < budgetPerFloor.length; i++) {
                floorsList.get(i).setBudget(budgetPerFloor[i]);
            }
            setupDialog.getDialogPane().lookupButton(ButtonType.OK).removeEventFilter(ActionEvent.ACTION,setupDialogEvent);
            setupDialog.close();
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
                setupDialog.setContentText("No ingresaste un presupuesto\ncorrecto, intentalo de nuevo");
            }
    }
    private void searchDevices(ActionEvent actionEvent){
        int input;
        actionEvent.consume();
        try {
            input = Integer.parseInt(setupDialog.getEditor().getText());
            int[][] indexesFound = logica.buscar(input,getArray());
            StringBuilder builder = new StringBuilder("El valor fue encontrado en los pisos y las oficinas\n");
            if (!Arrays.stream(indexesFound).allMatch(ints -> ints[0]==-1)){
                for (int[] index : indexesFound) {
                    if (index[0] != -1 && index[1] != -1) {
                        builder.append("Piso %d | Oficina %d \n".formatted(index[0], index[1]));
                    }
                }
                Alert show = new Alert(Alert.AlertType.INFORMATION);
                show.setTitle("Resultado busqueda");
                show.setHeaderText("Encontrado %d equipos en".formatted(input));
                show.setContentText(builder.toString());
                show.show();
            }else {
                setupDialog.setContentText("La cantidad de Equipos no fue encontrada.");
            }
            //setupDialog.getDialogPane().lookupButton(ButtonType.OK).removeEventFilter(ActionEvent.ACTION,setupDialogEvent);
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            setupDialog.setContentText("No ingresaste una cantidad de Equipos correcta, intentalo de nuevo");
        }
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
