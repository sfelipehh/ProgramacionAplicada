package com.sa.programacionaplicada.ResistenciasGUI;/*Author:sfeli*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import static java.lang.Math.pow;

public class ResistenciasViewController implements Initializable {
    private static final String omega = "\u03A9";
    @FXML
    private ToggleGroup selectionNumberBands;

    @FXML
    private ComboBox<ColoresResistencias> selectionFirstDigit;

    @FXML
    private ComboBox<ColoresResistencias> selectionSecondDigit;

    private final ComboBox<ColoresResistencias> selectionThirdDigit = new ComboBox<>();

    @FXML
    private ComboBox<ColoresResistencias> selectionMultiplier;

    @FXML
    private ComboBox<ColoresResistencias> selectionTolerance;

    @FXML
    private HBox colorsSelectionContainer;
    @FXML
    private Label resultLabel;

    @FXML
    private Button calculateButton;

    @FXML
    private ComboBox<String> selectionUnitsChange;

    @FXML
    private Label resultLabelUnits;

    private Double resistorValue = 0d;
    private String resistorUnit = "";

    private Background calculateButtonBackground;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Coloca el color de fondo del contenedor de los comboBox
        colorsSelectionContainer.setBackground(new Background(new BackgroundFill(Color.web("#fade12"),new CornerRadii(25),Insets.EMPTY)));
        //Guarda el color de fondo por defecto del botón
        calculateButtonBackground = calculateButton.getBackground();
        //Coloca el tamaño del comboBox para el tercer dígito
        selectionThirdDigit.setPrefWidth(50);

        setupPromptText();

        setupButtonCell();

        setupCellFactory();

        setupItems();

        //Coloca la función a ejecutar cuando cambie el valor de roundButton seleccionado
        selectionNumberBands.selectedToggleProperty().addListener(
                (observableValue, toggleOld, toggleNew) -> onChangeNumberBands(toggleNew));

        //Coloca la función a ejecutar cuando se dispare el botón
        calculateButton.setOnAction(this::calculate);
    }

    /**
     * Setup the prompt text of all ComboBoxes
     */
    private void setupPromptText(){
        selectionFirstDigit.setPromptText("1° Banda");
        selectionSecondDigit.setPromptText("2° Banda");
        selectionThirdDigit.setPromptText("3° Banda");
        selectionMultiplier.setPromptText("Múltiplo");
        selectionTolerance.setPromptText("Tolerancia");
        selectionUnitsChange.setPromptText("Unidades");
    }

    /**
     * Setup the button cell of all ComboBoxes
     */
    private void setupButtonCell(){
        selectionFirstDigit.setButtonCell((new SelectionCell()).create());
        selectionSecondDigit.setButtonCell((new SelectionCell()).create());
        selectionThirdDigit.setButtonCell((new SelectionCell()).create());
        selectionMultiplier.setButtonCell((new SelectionCell("multiplier")).create());
        selectionTolerance.setButtonCell((new SelectionCell("tolerance")).create());
    }

    /**
     * Setup the cell factory of all ComboBoxes
     */
    private void setupCellFactory(){
        selectionFirstDigit.setCellFactory((new SelectionCell())::factory);
        selectionSecondDigit.setCellFactory((new SelectionCell())::factory);
        selectionThirdDigit.setCellFactory((new SelectionCell())::factory);
        selectionMultiplier.setCellFactory((new SelectionCell("multiplier"))::factory);
        selectionTolerance.setCellFactory((new SelectionCell("tolerance"))::factory);
        selectionUnitsChange.setCellFactory(listView -> new ListCell<>(){

            {
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }

            @Override
            protected void updateItem(String s, boolean b) {
                super.updateItem(s, b);
                if (s == null || b){
                    setText(null);
                }else setText(s);
            }
        });
    }

    /**
     * Setup the items of all ComboBoxes
     */
    private void setupItems(){
        selectionFirstDigit.getItems().addAll(ColoresResistencias.getDigits());
        selectionSecondDigit.getItems().addAll(ColoresResistencias.getDigits());
        selectionThirdDigit.getItems().addAll(ColoresResistencias.getDigits());
        selectionMultiplier.getItems().addAll(ColoresResistencias.values());
        selectionTolerance.getItems().addAll(ColoresResistencias.getBasicTolerances());
        selectionUnitsChange.getItems().addAll("GigaOhm","MegaOhm", "KiloOhm", "Ohm", "miliOhm");
    }

    /**
     * Se ejecuta cuando se seleciona una u otra cantidad de bandas (4<->5)
     */
    private void onChangeNumberBands(Toggle toggleNew) {
        RadioButton selected = (RadioButton) toggleNew;
        if (selected.getId().equals("4-bands")) {
            colorsSelectionContainer.getChildren().remove(selectionThirdDigit);
            colorsSelectionContainer.setBackground(new Background(new BackgroundFill(Color.web("#fade12"),new CornerRadii(25),Insets.EMPTY)));
            selectionTolerance.getItems().removeAll(ColoresResistencias.getExtraTolerances());
        } else if (selected.getId().equals("5-bands")) {
            colorsSelectionContainer.getChildren().add(2, selectionThirdDigit);
            colorsSelectionContainer.setBackground(new Background(new BackgroundFill(Color.web("#145bd6"),new CornerRadii(25),Insets.EMPTY)));
            selectionTolerance.getItems().addAll(ColoresResistencias.getExtraTolerances());
        }
    }

    /**
     * Ejecuta la acción de calcular
     */
    private void calculate(ActionEvent actionEvent){
        calculateButton.setBackground(calculateButtonBackground);
        RadioButton selected = (RadioButton) selectionNumberBands.getSelectedToggle();
        double hundred=0;
        double ten=0;
        double unit=0;
        double multiplier;
        StringBuilder tolerance = new StringBuilder();
        try {
            if (selected.getId().equals("4-bands")) {
                ten = selectionFirstDigit.getValue().ordinal();
                unit = selectionSecondDigit.getValue().ordinal();
            } else if (selected.getId().equals("5-bands")) {
                hundred = selectionFirstDigit.getValue().ordinal();
                ten = selectionSecondDigit.getValue().ordinal();
                unit = selectionThirdDigit.getValue().ordinal();
            }
            multiplier = switch (selectionMultiplier.getValue()) {
                case DORADO -> -1;
                case PLATA -> -2;
                default -> selectionMultiplier.getValue().ordinal();
            };
            tolerance.append(switch (selectionTolerance.getValue()) {
                case CAFE -> "1";
                case ROJO -> "2";
                case VERDE -> "0.5";
                case AZUL -> "0.25";
                case VIOLETA -> "0.10";
                case GRIS -> "0.05";
                case DORADO -> "5";
                case PLATA -> "10";
                default -> "";
            });
            tolerance.append("%");
            resistorValue = ((hundred * 100) + (ten * 10) + (unit * 1)) * pow(10, multiplier);
            String withFormat = formatResistorValue(multiplier);

            resultLabel.setText( withFormat + resistorUnit + omega + " " + tolerance);
        }catch (NullPointerException e){
            resultLabel.setText("No has seleccionado todos los campos.");
            calculateButton.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY,Insets.EMPTY)));
        }
    }

    /**
     * Regresa el String con el valor de resistencia ajustado a un formato facil de leer
     */
    private String formatResistorValue(double multiplier){
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(3);
        if( multiplier<=4 && multiplier >=0){
            if (resistorValue / 1e3 >= 1){
                resistorUnit = "K";
                return numberFormat.format(resistorValue/1e3);
            }
        } else if (multiplier<=7) {
            if(resistorValue / 1e6 >=1){
                resistorUnit = "M";
                return numberFormat.format(resistorValue / 1e6);
            }
        } else if (multiplier<=10) {
            if(resistorValue / 1e8 >=1){
                resistorUnit = "G";
                return numberFormat.format(resistorValue/1e8);
            }
        }
        resistorUnit = "";
        return numberFormat.format(resistorValue);
    }

    private void onChangeUnits(String newValue){

    }
    private static class SelectionCell {
        private final String selectionType;
        public SelectionCell() {
            selectionType = "digit";
        }

        public SelectionCell(String selectionType) {
            this.selectionType = selectionType;
        }

        public ListCell<ColoresResistencias> create(){
            return factory(null);
        }
        public ListCell<ColoresResistencias> factory(ListView<ColoresResistencias> listView) {
            return new ListCell<>() {

                {
                    setContentDisplay(ContentDisplay.CENTER);
                    setAlignment(Pos.CENTER);
                }

                @Override
                protected void updateItem(ColoresResistencias item, boolean empty) {
                    super.updateItem(item, empty);
                    Background fondo;
                    ColoresResistencias color;
                    if (item == null || empty) {
                        setBackground(Background.EMPTY);
                        //setText(null);
                    } else {
                        color = item;
                        fondo = new Background(new BackgroundFill(color.getPaint(), CornerRadii.EMPTY, Insets.EMPTY));
                        setBackground(fondo);

                        /*StringBuilder textBuilder = new StringBuilder();
                        if (selectionType.equals("multiplier")) {
                            textBuilder.append(switch (color){
                                case NEGRO -> "1";
                                case CAFE -> "10";
                                case ROJO -> "100";
                                case NARANJA -> "1K";
                                case AMARILLO -> "10K";
                                case VERDE -> "100K";
                                case AZUL -> "1M";
                                case VIOLETA -> "10M";
                                case GRIS -> "100M";
                                case BLANCO -> "1G";
                                case DORADO -> "0.1";
                                case PLATA -> "0.01";
                            });
                            textBuilder.append(omega);
                        } else if (selectionType.equals("tolerance")) {
                            textBuilder.append(switch (color) {
                                case CAFE -> "1";
                                case ROJO -> "2";
                                case VERDE -> "0.5";
                                case AZUL -> "0.25";
                                case VIOLETA -> "0.10";
                                case GRIS -> "0.05";
                                case DORADO -> "5";
                                case PLATA -> "10";
                                default -> "";
                            });
                            textBuilder.append("%");
                        }else textBuilder.append(color.ordinal());
                        setText(textBuilder.toString());*/
                    }
                }
            };
        }
    }
}
