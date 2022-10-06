module com.sa.programacionaplicada {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.sa.programacionaplicada to javafx.fxml;
    opens com.sa.programacionaplicada.presentacion to javafx.fxml;
    exports com.sa.programacionaplicada;
}