package com.sa.programacionaplicada;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Button functions;

    @FXML
    private Button resistorCal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*functions.setText(Funciones.class.getSimpleName());
        resistorCal.setText(CalculadoraResistencia.class.getSimpleName());
        functions.setOnAction(this::runFunctions);
        resistorCal.setOnAction(this::runResistorCal);*/
    }


    private void setScene(String resource){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        Stage window = (Stage) title.getScene().getWindow();
        try {
            window.setScene(new Scene(loader.load(), 600, 400));
        }catch (IOException e){
            e.printStackTrace();
        }
        window.show();
    }
}