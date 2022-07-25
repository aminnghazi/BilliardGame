package com.example.billiardgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws Exception {
    GameView gameView = new GameView();
    gameView.start(HelloApplication.getStage());
    }
}