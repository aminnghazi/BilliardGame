module com.example.billiardgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.billiardgame to javafx.fxml;
    exports com.example.billiardgame;
}