module com.example.ball {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.ball to javafx.fxml;
    exports com.example.ball;
}