package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.sa.programacionaplicada.HelloApplication;
import com.sa.programacionaplicada.logica.Figura;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class FigurasViewController<T extends Figura> extends CalculatorBaseController{

    private Method calutateMethod;
    private final Method[] parameterSetMethod = new Method[2];
    private Method resultGetMethod;
    private T figura;
    @FXML
    private Label titleLabel;
    @FXML
    private VBox parametersContainer;
    @FXML
    private Button doCalculationButton;
    @FXML
    private Label resultLabel;
    private void doCalculation(ActionEvent actionEvent){
        for (int i = 0; i < parameterSetMethod.length; i++){
            HBox hboxParameter = (HBox) parametersContainer.getChildren().get(i);
            Method methodParameter = parameterSetMethod[i];
            if (hboxParameter.getId().equals(methodParameter.getName())){
                String textInput = ((TextField) hboxParameter.getChildren().get(1)).getText();
                try {
                    methodParameter.invoke(figura,Double.parseDouble(textInput));
                } catch (InvocationTargetException | IllegalAccessException ignored) {
                }catch (NumberFormatException notANumber){
                    System.out.println("No se ha ingresado un numero");
                    return;
                }
            }
        }

        try {
            calutateMethod.invoke(figura);
        } catch (IllegalAccessException | InvocationTargetException ignored) {
        }

        try {
            double result = (double) resultGetMethod.invoke(figura);
            resultLabel.setText("%.4f".formatted(result));
            resultLabel.setVisible(true);
        }catch (IllegalAccessException | InvocationTargetException ignored){

        }
    }
    public void buildView(Class<? extends Figura> claseFigura) throws IOException {
        try {
            figura = (T) claseFigura.getConstructor().newInstance();
            titleLabel.setText(claseFigura.getSimpleName());
        }catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored){

        }
        for (Method method : claseFigura.getDeclaredMethods()) {
             if (method.getName().contains("get")){
                 resultGetMethod = method;
             }
             if (method.getName().contains("set")){
                 if (parameterSetMethod[0] != null){
                     parameterSetMethod[1] = method;
                 }else {
                     parameterSetMethod[0] = method;
                 }
             }
             if (method.getName().contains("calcular")){
                 calutateMethod = method;
                 doCalculationButton.setText(doCalculationButton.getText() + " " + method.getName().replace("calcular",""));
             }
         }
        for (Method parameterSetMethod : parameterSetMethod){
            if (parameterSetMethod != null) {
                HBox parameterContainer = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("campo-entrada-layout.fxml")));
                parameterContainer.setId(parameterSetMethod.getName());
                for (Node node : parameterContainer.getChildren()) {
                    if (node.getId().equals("parameterName")) {
                        ((Label) node).setText(parameterSetMethod.getName().replace("set", ""));
                    }
                }

                parametersContainer.getChildren().add(parameterContainer);
            }
        }
        doCalculationButton.setOnAction(this::doCalculation);
    }
    @Override
    public String getCalculatorName() {
        return figura.getClass().getSimpleName();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
