module com.example.revisionsgbd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.revisionsgbd to javafx.fxml;
    exports com.example.revisionsgbd;
}