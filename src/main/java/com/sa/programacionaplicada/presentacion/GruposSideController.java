package com.sa.programacionaplicada.presentacion;/*Author:sfeli*/

import com.jfoenix.controls.JFXTabPane;
import com.sa.programacionaplicada.logica.Asignaturas;
import com.sa.programacionaplicada.presentacion.grupos.GruposController;
import com.sa.programacionaplicada.presentacion.grupos.ModifyAnyGrupoController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class GruposSideController {
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
    private GruposController searchController;
    @FXML
    private ModifyAnyGrupoController modifyController;
    public void searchAll(Event event) {
        Asignaturas.consultarTodos();
        searchController.searchAll();
    }

    public void resetModify(Event event) {
        //modifyController.reset();
    }
}
