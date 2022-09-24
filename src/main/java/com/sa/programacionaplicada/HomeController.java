package com.sa.programacionaplicada;

import com.sa.programacionaplicada.procesamientoDatos.logica.DistribucionBidimensional;
import com.sa.programacionaplicada.procesamientoDatos.logica.DistribucionUnidimensional;
import javafx.event.ActionEvent;
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
    private Button button1;

    @FXML
    private Button button2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button1.setText(DistribucionUnidimensional.class.getSimpleName());
        button1.setOnAction(this::runDistribucionEmpresa);
        button2.setText(DistribucionBidimensional.class.getSimpleName());
        button2.setOnAction(this::runDistribucionEdificio);
    }

    private void runDistribucionEmpresa(ActionEvent actionEvent){
        setScene("distribucion-empresa-view.fxml");
    }
    private void runDistribucionEdificio(ActionEvent actionEvent){
        setScene("distribucion-edificio-view.fxml");
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