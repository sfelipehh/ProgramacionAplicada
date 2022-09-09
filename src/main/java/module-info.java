module com.sa.programacionaplicada {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.sa.programacionaplicada to javafx.fxml;
    opens com.sa.programacionaplicada.funcionesGUI to javafx.fxml;
    opens com.sa.programacionaplicada.ResistenciasGUI to javafx.fxml;
    exports com.sa.programacionaplicada.funcionesGUI;
    exports com.sa.programacionaplicada.ResistenciasGUI;
    exports com.sa.programacionaplicada;
}