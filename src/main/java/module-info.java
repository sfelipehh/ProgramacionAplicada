module com.sa.programacionaplicada {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.j;

    opens com.sa.programacionaplicada to javafx.fxml;
    opens com.sa.programacionaplicada.presentacion to javafx.fxml;
    opens com.sa.programacionaplicada.presentacion.conductor to javafx.fxml;
    exports com.sa.programacionaplicada;
    exports com.sa.programacionaplicada.logica;
    opens com.sa.programacionaplicada.logica to javafx.base, javafx.fxml;
}