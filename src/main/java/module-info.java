module org.example.epicflow {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.epicflow to javafx.fxml;
    exports org.example.epicflow;
    opens org.example.epicflow.controller to javafx.fxml;
    exports org.example.epicflow.controller;

}