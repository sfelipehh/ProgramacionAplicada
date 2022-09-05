module com.sa.programacionaplicada {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.sa.programacionaplicada to javafx.fxml;
    exports com.sa.programacionaplicada;
}