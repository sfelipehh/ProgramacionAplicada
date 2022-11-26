package com.sa.programacionaplicada;

import com.sa.programacionaplicada.logica.Conductores;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-screen-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Programación Aplicada");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Conductores conductores1 = new Conductores();
        System.out.println(conductores1.ConsultarConductorPorId(1));

    }

    public static void main(String[] args) {
        launch();
    }
}