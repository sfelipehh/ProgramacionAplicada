package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenController {
    @FXML
    private HBox container;

    @FXML
    private void loadConductoresSide(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("conductores-side-view.fxml"));
        Stage window = (Stage) container.getScene().getWindow();
        try {
            window.setScene(new Scene(loader.load(), 600, 600));
        }catch (IOException e){
            e.printStackTrace();
        }
        window.show();
    }
    @FXML
    private void loadVehiculosSide(ActionEvent actionEvent){}
}
