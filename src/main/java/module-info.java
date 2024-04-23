module com.example.learnscience {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.learnscience to javafx.fxml;
    exports com.example.learnscience;
}