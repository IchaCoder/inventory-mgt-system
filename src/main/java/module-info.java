module com.example.group {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.group to javafx.fxml;
    exports com.example.group;
}