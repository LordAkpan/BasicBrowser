module com.example.browserfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.example.browserfx to javafx.fxml;
    exports com.example.browserfx;
}