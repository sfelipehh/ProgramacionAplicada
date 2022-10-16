package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.HelloApplication;
import com.sa.programacionaplicada.logica.Figura;
import com.sa.programacionaplicada.logica.Rombo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;

import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

public class CalculadoraController implements Initializable {
    @FXML
    CalculatorBaseController calculatorController;
    @FXML
    TitledPane containerPane;
    @FXML
    Menu menu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        containerPane.setText(calculatorController.getCalculatorName() + "       ");
    }
    @FXML
    private void calculatorChange(ActionEvent actionEvent){
        String selectedCalculator = null;
        if (actionEvent.getSource() instanceof MenuItem){
            selectedCalculator = ((MenuItem) actionEvent.getSource()).getId();
        }
        if (Objects.requireNonNull(selectedCalculator).contains("figuras")){
            selectFigura(selectedCalculator.replace("figuras",""));
        }
        if (Objects.requireNonNull(selectedCalculator).equals("scientific")){
            selectScientific();
        }
        if (Objects.requireNonNull(selectedCalculator).equals("bit")){
            selectProgrammer();
        }
    }

    private void selectFigura(String figuraName){
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("figura-base-view.fxml"));
        try {
            containerPane.setContent(loader.load());
            calculatorController = loader.getController();
            Class<?> clase = Class.forName("com.sa.programacionaplicada.logica." + figuraName);
            ((FigurasViewController<?>) calculatorController).buildView((Class<? extends Figura>) clase);
            containerPane.setText(calculatorController.getCalculatorName() + "       ");
        } catch (IOException resourceNotFound) {
            throw new MissingResourceException(resourceNotFound.getLocalizedMessage(),getClass().getName(),"figura-base-view.fxml");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void selectScientific(){
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("cientifica-view.fxml"));
        try {
            containerPane.setContent(loader.load());
            calculatorController = loader.getController();
            containerPane.setText(calculatorController.getCalculatorName() + "       ");
        } catch (IOException resourceNotFound) {
            throw new MissingResourceException(resourceNotFound.getLocalizedMessage(), getClass().getName(), "cientifica-view.fxml");
        }
    }
    private void selectProgrammer() {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("programador-view.fxml"));
        try {
            containerPane.setContent(loader.load());
            calculatorController = loader.getController();
            containerPane.setText(calculatorController.getCalculatorName() + "       ");
        } catch (IOException resourceNotFound) {
            throw new MissingResourceException(resourceNotFound.getLocalizedMessage(), getClass().getName(), "programador-view.fxml");
        }
    }
}
