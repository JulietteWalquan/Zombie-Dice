module com.example.zombiedice {
    requires javafx.controls;
    requires javafx.fxml;


    opens application to javafx.fxml;
    exports application;
    exports classes;
    opens classes to javafx.fxml;
}