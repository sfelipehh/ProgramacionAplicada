package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.jfoenix.controls.JFXTabPane;
import com.sa.programacionaplicada.presentacion.conductor.ModifyAnyConductoresController;
import com.sa.programacionaplicada.presentacion.conductor.SearchAllConductoresContoller;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class ConductoresSideController {
    @FXML
    private JFXTabPane tabsContainer;
    @FXML
    private Tab searchAllTab;
    @FXML
    private Tab searchAny;
    @FXML
    private Tab modifyAny;
    @FXML
    private Tab deleteAny;
    @FXML
    private SearchAllConductoresContoller searchController;
    @FXML
    private ModifyAnyConductoresController modifyController;
    public void searchAll(Event event) {
        searchController.searchAll();
    }

    public void resetModify(Event event) {
        modifyController.reset();
    }
}
