module com.example.aurasync {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.aurasync to javafx.fxml;
    exports com.example.aurasync;
}