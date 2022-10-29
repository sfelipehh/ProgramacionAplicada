package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.logica.LeydeOhm;
import com.sa.programacionaplicada.logica.ResistenciaEqui;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

public class LeyOhmController{

    public TextField numeroResistencias;
    public TextField valorResistenciaField;
    public Label resistorList;
    public TextField valorEntradaVOI;
    public Label resistenciaEquivalenteLabel;
    public Label voltajeLabel;
    public Label corrienteLabel;
    public Label potencia1Label;
    public Label potencia3Label;
    public Label potencia2Label;
    public ToggleGroup tipoArreglo;
    public ToggleGroup valorACalcular;

    private Double voltaje;
    private Double corriente;
    private Double potencia;
    private Double resistencia;
    private ResistenciaEqui calREquivalente = new ResistenciaEqui();
    private LeydeOhm calLeyOhm = new LeydeOhm();
    private ArrayList<Double> resistencias = new ArrayList<>();
    public void acceptNumberResistors(ActionEvent actionEvent) {
        resistencias = new ArrayList<>((int) Double.parseDouble(numeroResistencias.getText()));
    }

    public void addResistor(ActionEvent actionEvent) {
        double valorResistencia = Double.parseDouble(valorResistenciaField.getText());
        resistencias.add(valorResistencia);
        resistorList.setText("Valores Introducidos:" + resistencias);
        valorResistenciaField.setText(null);
    }

    public void calculateVOI(ActionEvent actionEvent) {
        calREquivalente.setValoresResistencia(resistencias);
        if (tipoArreglo.getSelectedToggle()!=null) {
            if (((ToggleButton) tipoArreglo.getSelectedToggle()).getId().equals("serie")) {
                resistencia = calREquivalente.calcularRenserie();
            } else {
                resistencia = calREquivalente.calcularRparalelo();
            }
            resistenciaEquivalenteLabel.setText("Resistencia equivalente: " + "%.3f".formatted(resistencia) + " Ohmios");
        }
        if (tipoArreglo.getSelectedToggle()!=null) {
            if (((ToggleButton) valorACalcular.getSelectedToggle()).getId().equals("corriente")) {
                voltaje = Double.parseDouble(valorEntradaVOI.getText());
                corriente = calLeyOhm.calcularCorriente(voltaje, resistencia);
            } else {
                corriente = Double.parseDouble(valorEntradaVOI.getText());
                voltaje = calLeyOhm.calcularVoltaje(corriente, resistencia);
            }
        }
        voltajeLabel.setText("Voltaje: " + voltaje + " Voltios");
        corrienteLabel.setText("Corriente: " + corriente + " Amperios");
        potencia = calLeyOhm.calcularPot1(voltaje,resistencia);

        potencia1Label.setText("Potencia 1 (V*I) : " + "%.3f".formatted(calLeyOhm.calcularPot1(voltaje, corriente)) + " Vatios");
        potencia2Label.setText("Potencia 2 (I^2*R) : " + "%.3f".formatted(calLeyOhm.calcularPot2(corriente,resistencia)) + " Vatios");
        potencia3Label.setText("Potencia 3 (V^2/R) : " + "%.3f".formatted(calLeyOhm.calcularPot3(voltaje,resistencia)) + " Vatios");


    }

}
